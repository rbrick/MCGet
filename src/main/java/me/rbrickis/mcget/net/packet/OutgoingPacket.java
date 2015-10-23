package me.rbrickis.mcget.net.packet;

import java.io.DataInput;

public abstract class OutgoingPacket extends AbstractPacket {

    public OutgoingPacket(int id) {
        super(id);
    }

    @Override
    public void read(DataInput input) {
    }
}
