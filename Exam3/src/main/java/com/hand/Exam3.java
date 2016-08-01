package com.hand;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Exam3 {

	public static void main(String[] args) {
		
		new Read().start();
		
	}
	
}

class Read extends Thread{
	
	@Override
	public void run() {
		
		try {
			
			URL url = new URL("http://hq.sinajs.cn/list=sz300170");
			URLConnection urlcon = url.openConnection();
			
			InputStream is = urlcon.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			
			FileOutputStream fos = new FileOutputStream("exam3.txt");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			
			FileInputStream fis = new FileInputStream("D:"+File.separator+"git"+File.separator+"NetScoketExam"+File.separator+"Exam3"+File.separator+"exam3.txt");
			InputStreamReader id = new InputStreamReader(fis,"UTF-8");
			BufferedReader bf = new BufferedReader(id);
			
			byte byt [] = new byte [6];
			int len =-1;
			StringBuffer sb = new StringBuffer();
			while( (len=bis.read(byt))!=-1 ){
				bos.write(byt, 0, len);
			}
			bos.close();
			bis.close();
			
			System.out.println("ok");
			sb.append(bf.readLine());
			System.out.println(sb.toString());
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}