package me.rbrickis.mcget.net;

import me.rbrickis.mcget.net.packet.IncomingPacket;

public interface PacketHandler {

    void handle(MinecraftClient client, IncomingPacket packet);

}
