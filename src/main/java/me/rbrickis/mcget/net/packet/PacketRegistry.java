package me.rbrickis.mcget.net.packet;

import me.rbrickis.mcget.net.packet.packets.status.incoming.StatusPongPacket;
import me.rbrickis.mcget.net.packet.packets.status.incoming.StatusResponsePacket;

public final class PacketRegistry {

    public static final PacketMap INCOMING_STATUS_MAP = new PacketMap();


    static {
        {
            INCOMING_STATUS_MAP.register(0x00, StatusResponsePacket.class);
            INCOMING_STATUS_MAP.register(0x01, StatusPongPacket.class);
        }
    }

}
