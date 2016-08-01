package com.hand;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Exam1 {

	public static void main(String[] args) {
		
		new ReadByGet().start();
		
	}
	
}

class ReadByGet extends Thread{
	
	@Override
	public void run() {
		
		try {
			
			URL url = new URL("http://files.saas.hand-china.com/java/target.pdf");
			URLConnection urlcon = url.openConnection();
			
			InputStream is = urlcon.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			
			FileOutputStream fos = new FileOutputStream("target.pdf");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			
			byte byt [] = new byte [6];
			int len =-1;
			int counts = 0;
			while( (len=bis.read(byt))!=-1 ){
				bos.write(byt, 0, len);
				counts++;
			}
			bos.close();
			bis.close();
			
			System.out.println("ok");
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	
}
