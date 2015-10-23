package me.rbrickis.mcget.net.packet;

import java.util.HashMap;
import java.util.Map;

public class PacketMap {

    private final Map<Integer, Class<? extends Packet>> idToPacket = new HashMap<>();
    private final Map<Class<? extends Packet>, Integer> packetToId = new HashMap<>();


    public void register(int id, Class<? extends Packet> packetClazz) {
        idToPacket.put(id, packetClazz);
        packetToId.put(packetClazz, id);
    }

    public Class<? extends Packet> fromId(int id) {
        if (!idToPacket.containsKey(id)) {
            return null;
        }
        return idToPacket.get(id);
    }

    public int getId(Class<? extends Packet> packetClazz) {
        if (!packetToId.containsKey(packetClazz)) {
            return -1;
        }
        return packetToId.get(packetClazz);
    }



}
