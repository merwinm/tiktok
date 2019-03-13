package packets.game;

import client.ServerHandler;
import server.ClientHandler;
import server.Packet;

public class PacketMarkX extends Packet {
    int value = 2;
    int xpos;
    int ypos;

    PacketMarkX(int x, int y){
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
