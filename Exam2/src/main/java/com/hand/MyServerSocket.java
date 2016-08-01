package com.hand;

public class MyServerSocket {

	public static void main(String[] args) {
		
		new ServerListener().start();
		System.out.println("The client is connected, please enter the localhost:12345 in the address bar.");
		
	}
	
}
