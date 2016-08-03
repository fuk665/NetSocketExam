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
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Exam3 {
	public static void main(String[] args) {
		new Read().start();
	}
}

class Read extends Thread{
	
	@Override
	public void run() {
		
		try {
			//从网页下载
			URL url = new URL("http://hq.sinajs.cn/list=sz300170");
			URLConnection urlcon = url.openConnection();
			//准备读字节流
			InputStream is = urlcon.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			InputStreamReader rrr = new InputStreamReader(bis,"GBK");
			//写字节流
			FileOutputStream fos = new FileOutputStream("src/exam3.txt");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			OutputStreamWriter www = new OutputStreamWriter(bos,"UTF-8");
			
			//再读本地文件
			FileInputStream fis = new FileInputStream("src/exam3.txt");
			InputStreamReader id = new InputStreamReader(fis,"UTF-8");
			BufferedReader bf = new BufferedReader(id);
			
			char byt [] = new char [1];
			int len =-1;
			//边度边写
			while( (len=rrr.read(byt))!=-1 ){
				www.write(byt, 0, len);
				www.flush();
			}
			bos.close();
			bis.close();
			
			StringBuffer sb = new StringBuffer();
			sb.append(bf.readLine());
			String str = sb.toString();
			System.out.println(str);
			//分割=得到的字符串到字符串数组s
			String [] s = str.split("=");
			String s1 = s[1] ;
			//=右边的作为根节点
			s1 = s1.substring(1);
			String s0 = s[0] ;
			//分割=右边的作为子节点
			String [] sa = s1.split(",");
			int end = sa.length-1;
			sa[end] = sa[end].substring(0, sa[end].length()-2);
			sa[end-2] = sa[end-2]+":"+sa[end-1]+":"+sa[end] ;
			sa[end-1] = null;
			sa[end] = null;
			//可添加一步换新数组
			
			try {
				//创建DOM解析
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				Document document = factory.newDocumentBuilder().newDocument();
				//创建子节点的name和里面的内容
				Element e0 = document.createElement("stock");
				Element e1 = document.createElement("name");
				e1.setTextContent(sa[0]);
				Element e2 = document.createElement("open");
				e2.setTextContent(sa[1]);
				Element e3 = document.createElement("close");
				e3.setTextContent(sa[2]);
				Element e4 = document.createElement("current");
				e4.setTextContent(sa[3]);
				Element e5 = document.createElement("hight");
				e5.setTextContent(sa[4]);
				Element e6 = document.createElement("low");
				e6.setTextContent(sa[5]);
				//添加子节点
				e0.appendChild(e1);
				e0.appendChild(e2);
				e0.appendChild(e3);
				e0.appendChild(e4);
				e0.appendChild(e5);
				e0.appendChild(e6);
				//准备创建JSON
				JsonObject jo = new JsonObject();
				JsonArray ja = new JsonArray();
				//创建JSON，类似于键值对的键
				JsonObject jo1 = new JsonObject();
				jo1.addProperty("name", sa[0]);
				jo1.addProperty("open", sa[1]);
				jo1.addProperty("close", sa[2]);
				jo1.addProperty("current", sa[3]);
				jo1.addProperty("hight", sa[4]);
				jo1.addProperty("low", sa[5]);
				
				ja.add(jo1);
				//设置JSON数据里面的内容
				Element [] ele = new Element [sa.length-8] ;
				for(int j=0;j<sa.length-9;j++){
					ele[j] = document.createElement("other");
					ele[j].setTextContent(sa[j+6]);
					e0.appendChild(ele[j]);
					
					jo1.addProperty("other", sa[j+6]);
					ja.add(jo1);
					
				}
				//把子数组添加到父数组
				jo.add("stock", ja);
				//把子节点添加到父节点里面
				Element e7 = document.createElement("time");
				e7.setTextContent(sa[end-2]);
				e0.appendChild(e7);
				
				document.appendChild(e0);
				//下面用来转换数据格式
				TransformerFactory tfact = TransformerFactory.newInstance();
				Transformer tb = tfact.newTransformer();
				StringWriter sw = new StringWriter();
				
				/**
				 * 只有通过StreamResult(Writer)构造函数生成才能正确设置缩进
				 * （通过OutputStream或者File生成的StreamResult是无法设置缩进的
				 */
				tb.setOutputProperty(OutputKeys.INDENT,"yes");
				tb.setOutputProperty(OutputKeys.METHOD, "xml");
				
			//	tb.transform(new DOMSource(document), new StreamResult(sw));
			//	System.out.println(sw.toString());
				//把document文档转成xml文件
				tb.transform(new DOMSource(document), new StreamResult(new File("Exam3.xml")));
				System.out.println("XML has been created!");
				//把json转成字符串
				String strJson = jo.toString();
			//	System.out.println(strJson);	//此处得到的是乱码
				
				FileOutputStream fosJson = new FileOutputStream(new File("Exam3.gson"));
				OutputStreamWriter oswJson = new OutputStreamWriter(fosJson,"UTF-8");
				//把字符串写入到gson
				oswJson.write(strJson);
				oswJson.close();
				fosJson.close();
				System.out.println("Json has been created!");
				
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (TransformerConfigurationException e) {
				e.printStackTrace();
			} catch (TransformerException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
