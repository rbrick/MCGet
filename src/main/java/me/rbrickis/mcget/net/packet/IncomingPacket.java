package me.rbrickis.mcget.net.packet;

import java.io.DataOutput;

public abstract class IncomingPacket extends AbstractPacket {

    public IncomingPacket(int id) {
        super(id);
    }

    @Override
    public void write(DataOutput output) {
    }
}
