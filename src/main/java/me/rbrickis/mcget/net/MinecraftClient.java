package me.rbrickis.mcget.net;

import lombok.Getter;
import me.rbrickis.mcget.net.packet.IncomingPacket;
import me.rbrickis.mcget.net.packet.OutgoingPacket;
import me.rbrickis.mcget.net.packet.Packet;
import me.rbrickis.mcget.net.packet.PacketRegistry;
import me.rbrickis.mcget.utils.BufferUtils;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Serves as a Minecraft client
 */
public class MinecraftClient {

    @Getter private Socket connection;
    @Getter private DataInputStream inputStream;
    @Getter private DataOutputStream outputStream;
    @Getter private PacketHandler handler;
    @Getter private ReaderThread readerThread;

    public MinecraftClient(String host, short port, PacketHandler handler) {
        this(new InetSocketAddress(host, port), handler);
    }

    public MinecraftClient(SocketAddress address, PacketHandler handler) {
        this.connection = new Socket(Proxy.NO_PROXY);

        try {
            this.connection.setKeepAlive(true);
            this.connection.setSoTimeout(5000);
            this.connection.connect(address);
            this.inputStream = new DataInputStream(connection.getInputStream());
            this.outputStream = new DataOutputStream(connection.getOutputStream());
            this.handler = handler;
            this.readerThread = new ReaderThread(this);
            this.readerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void close() {
        this.readerThread.cancel();

        try {
            this.outputStream.flush();
            this.outputStream.close();

            this.inputStream.close();
            this.connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a packet
     * Length : VarInt = Length of packet data + length of packet ID
     * Packet ID : VarInt = Packet ID
     * Data : ByteArray
     *
     * @param packet - Packet to send
     * @throws IOException
     */
    public void send(OutgoingPacket packet) throws IOException {

        DataOutputStream header = new DataOutputStream(new ByteArrayOutputStream());
        BufferUtils.writeVarInt(header, packet.getPacketId());

        ByteArrayOutputStream bodyStream = new ByteArrayOutputStream();
        DataOutputStream body = new DataOutputStream(bodyStream);
        packet.write(body);

        int packetLength = body.size() + header.size(); // Length of the packet
        BufferUtils.writeVarInt(outputStream, packetLength); // Write the length
        BufferUtils.writeVarInt(outputStream, packet.getPacketId()); // Write the packet ID
        outputStream.write(bodyStream.toByteArray());
        outputStream.flush();
    }

    /**
     * Reads a packet
     */
    public IncomingPacket read() {

        try {
            int length = BufferUtils.readVarInt(inputStream);
            int id = BufferUtils.readVarInt(inputStream);

            Class<? extends Packet> packetClass = PacketRegistry.INCOMING_STATUS_MAP.fromId(id);

            if (packetClass != null) {
                Packet packet = packetClass.newInstance();
                packet.read(inputStream);
                return (IncomingPacket) packet;
            }

        } catch (IOException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }


}
