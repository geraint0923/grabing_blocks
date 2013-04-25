package com.thu.grabingblocks.network;




public class ConnectionManager {
	private ConnectionManager() {
		
	}
	
	private static ConnectionManager instance;
	
	public ConnectionManager getInstance(){
		if(instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}
}