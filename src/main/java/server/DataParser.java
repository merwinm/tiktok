package server;

import client.ServerHandler;

public interface DataParser<T> {

    public T parseAtServer(ClientHandler handler);

    public T parseAtClient(ServerHandler handler);

}
