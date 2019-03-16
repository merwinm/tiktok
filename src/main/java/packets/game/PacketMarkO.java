package packets.game;

import client.ServerHandler;
import server.ClientHandler;
import server.Packet;

public class PacketMarkO extends Packet {
    int value = 1;
    int xpos;
    int ypos;

    PacketMarkO(int x, int y){
        this.xpos = x;
        this.ypos = y;
    }

    @Override
    public Object parseAtServer(ClientHandler handler) {
        return null;
    }

    @Override
    public Object parseAtClient(ServerHandler handler) {
        return null;
    }
}
