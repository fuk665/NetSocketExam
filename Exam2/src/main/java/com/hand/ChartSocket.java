package com.hand;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChartSocket extends Thread {

	Socket socket ;
	
	public ChartSocket(Socket socket){
		this.socket=socket;
	}
	
	public void out(){
		
		try {
			//D:\git\NetScoketExam\Exam1\target.pdf
			FileInputStream fis = new FileInputStream("D:"+File.separator+"git"+File.separator+"NetScoketExam"+File.separator+"Exam1"+File.separator+"target.pdf");
			FileOutputStream fos = new FileOutputStream("target.pdf");
			byte byt [] = new byte [10];
			int len=-1;
			while((len=fis.read(byt))!=-1){
				fos.write(byt, 0, len);
				//socket.getOutputStream().write(byt, 0, len);
			}
			fos.close();
			fis.close();
			
			System.out.println("ok!");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		out();
	}

}
