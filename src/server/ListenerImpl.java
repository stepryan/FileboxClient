package server;

import filebox.listenerPOA;

public class ListenerImpl extends listenerPOA{

	@Override
	public void message(String message) {
	System.out.println("Message from the Server:" + message);
	}

}
