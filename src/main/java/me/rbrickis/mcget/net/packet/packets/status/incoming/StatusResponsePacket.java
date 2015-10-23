package me.rbrickis.mcget.net.packet.packets.status.incoming;

import lombok.Getter;
import me.rbrickis.mcget.net.packet.IncomingPacket;
import me.rbrickis.mcget.utils.BufferUtils;

import java.io.DataInput;
import java.io.IOException;

public class StatusResponsePacket extends IncomingPacket {

    @Getter private String statusResponse;

    public StatusResponsePacket() {
        super(0x00);
    }

    @Override
    public void read(DataInput input) {
        try {
            this.statusResponse = BufferUtils.readString(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
