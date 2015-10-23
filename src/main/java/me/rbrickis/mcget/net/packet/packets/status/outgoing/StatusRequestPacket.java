package me.rbrickis.mcget.net.packet.packets.status.outgoing;

import me.rbrickis.mcget.net.packet.OutgoingPacket;

import java.io.DataOutput;

public class StatusRequestPacket extends OutgoingPacket {

    public StatusRequestPacket() {
        super(0x00);
    }

    @Override
    public void write(DataOutput output) {
      // nothing gets sent
    }
}
