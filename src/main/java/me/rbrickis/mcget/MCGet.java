package me.rbrickis.mcget;

import me.rbrickis.mcget.net.MinecraftClient;
import me.rbrickis.mcget.net.PacketHandler;
import me.rbrickis.mcget.net.packet.IncomingPacket;
import me.rbrickis.mcget.net.packet.packets.handshake.HandshakePacket;
import me.rbrickis.mcget.net.packet.packets.status.incoming.StatusPongPacket;
import me.rbrickis.mcget.net.packet.packets.status.incoming.StatusResponsePacket;
import me.rbrickis.mcget.net.packet.packets.status.outgoing.StatusPingPacket;
import me.rbrickis.mcget.net.packet.packets.status.outgoing.StatusRequestPacket;

public class MCGet {

    /*
       I'd much rather use Netty...
     */
    public static void main(String[] args) throws Exception {
        MinecraftClient client =
            new MinecraftClient("us.mineplex.com", (short) 25565, new PacketHandler() {
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

        client.send(new HandshakePacket(client.getConnection().getInetAddress().getHostAddress(),
            client.getConnection().getPort()));
        client.send(new StatusRequestPacket());
        client.send(new StatusPingPacket());

    }


}
