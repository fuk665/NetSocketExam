package com.hand;

public class MyServerSocket {

	public static void main(String[] args) {
		
		new ServerListener().start();
		System.out.println("OK,请输入nettel localhost:12345或者地址栏输入localhost:12345");
		
	}
	
}
