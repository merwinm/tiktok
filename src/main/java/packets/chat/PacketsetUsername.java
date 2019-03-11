package packets.chat;

import server.Packet;

public class PacketsetUsername extends Packet {
    String name;

    PacketsetUsername(String name){
        this.name = name;
    }
    @Override
    public Object parse() {

        return null;
    }
}
