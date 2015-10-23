package me.rbrickis.mcget.net;

import me.rbrickis.mcget.net.packet.IncomingPacket;

public class ReaderThread extends Thread {

    private MinecraftClient client;
    private boolean running;

    public ReaderThread(MinecraftClient client) {
        this.client = client;
        this.running = true;
        setName("ReaderThread");
    }

    @Override
    public void run() {
        while (running) {
            IncomingPacket packet = client.read();

            if (packet != null) {
                client.getHandler().handle(client, packet);
            }

        }
    }

    public void cancel() {
        this.running = false;
    }
}
