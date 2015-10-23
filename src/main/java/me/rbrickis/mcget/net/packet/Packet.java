package me.rbrickis.mcget.net.packet;

import java.io.DataInput;
import java.io.DataOutput;

public interface Packet {

    void write(DataOutput output);

    void read(DataInput input);

    int getPacketId();

}
