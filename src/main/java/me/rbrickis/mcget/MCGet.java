package me.rbrickis.mcget;


import me.rbrickis.mcget.net.MinecraftClient;
import me.rbrickis.mcget.net.PacketHandler;
import me.rbrickis.mcget.net.packet.IncomingPacket;
import me.rbrickis.mcget.net.packet.packets.handshake.HandshakePacket;
import me.rbrickis.mcget.net.packet.packets.status.incoming.StatusPongPacket;
import me.rbrickis.mcget.net.packet.packets.status.incoming.StatusResponsePacket;
import me.rbrickis.mcget.net.packet.packets.status.outgoing.StatusPingPacket;
import me.rbrickis.mcget.net.packet.packets.status.outgoing.StatusRequestPacket;

import java.io.IOException;

public class MCGet {
    /*
       I'd much rather use Netty...
     */
    public static void main(String[] args) {
        MinecraftClient client =
            new MinecraftClient("minehq.com", (short) 25565, new PacketHandler() {
                @Override
                public void handle(MinecraftClient client, IncomingPacket packet) {
                    if (packet instanceof StatusResponsePacket) {
                        System.out.println(((StatusResponsePacket) packet).getStatusResponse());
                    } else if (packet instanceof StatusPongPacket) {
                        System.out.println("Done pinging server.");
                        client.close();
                    }
                }
            });

        try {
            client.send(new HandshakePacket());
            client.send(new StatusRequestPacket());
            client.send(new StatusPingPacket());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
