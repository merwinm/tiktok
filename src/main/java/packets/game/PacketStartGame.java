package packets.game;

import client.ServerHandler;
import server.ClientHandler;
import server.Packet;

public class PacketStartGame extends Packet {
    @Override
    public Object parseAtServer(ClientHandler handler) {
        return null;
    }

    @Override
    public Object parseAtClient(ServerHandler handler) {
        return null;
    }
}
