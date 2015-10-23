package me.rbrickis.mcget.net.packet.packets.handshake;

import me.rbrickis.mcget.net.packet.OutgoingPacket;
import me.rbrickis.mcget.utils.BufferUtils;

import java.io.DataOutput;
import java.io.IOException;

public class HandshakePacket extends OutgoingPacket {

    private String host;
    private int port;

    public HandshakePacket(String host, int port) {
        super(0x00);
        this.host = host;
        this.port = port;
    }

    @Override
    public void write(DataOutput output) {
        try {
            BufferUtils.writeVarInt(output, 47);
            BufferUtils.writeString(output, host);
            output.writeShort(port);
            BufferUtils.writeVarInt(output, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
