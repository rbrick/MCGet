package me.rbrickis.mcget.net;

import me.rbrickis.mcget.net.packet.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Serves as a Minecraft client
 */
public class MinecraftClient {

    private Socket connection;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;


    public MinecraftClient(String host, short port) {
        this(new InetSocketAddress(host, port));
    }

    public MinecraftClient(SocketAddress address) {
        this.connection = new Socket(Proxy.NO_PROXY);
        try {
            this.connection.connect(address);
            this.inputStream = new DataInputStream(connection.getInputStream());
            this.outputStream = new DataOutputStream(connection.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void send(Packet packet) {
        
    }

}
