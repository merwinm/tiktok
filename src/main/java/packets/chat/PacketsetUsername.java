package packets.chat;

import client.ServerHandler;
import server.ClientHandler;
import server.Packet;

public class PacketsetUsername extends Packet {
    String name;

    public PacketsetUsername(String name){
        this.name = name;
    }

    @Override
    public Object parseAtServer(ClientHandler handler) {
        handler.setUsername(name);

        return null;
    }

    @Override
    public Object parseAtClient(ServerHandler handler) {
        return null;
    }
}
