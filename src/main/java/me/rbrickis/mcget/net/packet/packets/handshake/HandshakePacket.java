package me.rbrickis.mcget.net.packet.packets.handshake;

import me.rbrickis.mcget.net.packet.OutgoingPacket;
import me.rbrickis.mcget.utils.BufferUtils;

import java.io.DataOutput;
import java.io.IOException;

public class HandshakePacket extends OutgoingPacket {

    public HandshakePacket() {
        super(0x00);
    }

    @Override
    public void write(DataOutput output) {
        try {
            BufferUtils.writeVarInt(output, 47);
            BufferUtils.writeString(output, "localhost");
            output.writeShort(25565);
            BufferUtils.writeVarInt(output, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
