package me.rbrickis.mcget.net.packet.packets.status.incoming;

import lombok.Getter;
import me.rbrickis.mcget.net.packet.IncomingPacket;

import java.io.DataInput;
import java.io.IOException;

public class StatusPongPacket extends IncomingPacket {

    @Getter private long payload;

    public StatusPongPacket() {
        super(0x01);
    }

    @Override
    public void read(DataInput input) {
        try {
            this.payload = input.readLong();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
