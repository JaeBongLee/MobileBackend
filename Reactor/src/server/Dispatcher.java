package server;

import java.net.ServerSocket;

public interface Dispatcher {
	public void dispatch(ServerSocket serverSocket , HandleMap handleMap);
}
