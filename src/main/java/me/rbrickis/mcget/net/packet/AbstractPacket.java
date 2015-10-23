package me.rbrickis.mcget.net.packet;

public abstract class AbstractPacket implements Packet {

    private int id;

    public AbstractPacket(int id) {
        this.id = id;
    }

    @Override
    public int getPacketId() {
        return id;
    }
}
