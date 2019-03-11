package server;

import server.DataParser;

import java.io.Serializable;

public abstract class Packet implements DataParser, Serializable {

    public Packet get(){
        return this;
    }

}
