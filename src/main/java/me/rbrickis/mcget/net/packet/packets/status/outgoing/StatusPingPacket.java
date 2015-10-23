package me.rbrickis.mcget.net.packet.packets.status.outgoing;

import me.rbrickis.mcget.net.packet.OutgoingPacket;

import java.io.DataOutput;
import java.io.IOException;

public class StatusPingPacket extends OutgoingPacket {

    public StatusPingPacket() {
        super(0x01);
    }

    @Override
    public void write(DataOutput output) {
        try {
            output.writeLong(System.currentTimeMillis());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
