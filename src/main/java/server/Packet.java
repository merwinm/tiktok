package server;

import client.ServerHandler;
import server.DataParser;

import java.io.Serializable;

public abstract class Packet implements DataParser, Serializable {

     protected ClientHandler clientHandler;
     protected ServerHandler serverHandler;

}
