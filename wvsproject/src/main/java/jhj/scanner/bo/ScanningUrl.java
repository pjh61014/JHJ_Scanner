package jhj.scanner.bo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import com.mongodb.util.JSON;

import py4j.GatewayServer;

public class ScanningUrl {

	private Stack stack;


	public ScanningUrl() {

	}

	public ScanningUrl(String url) {
		super();
		stack = new Stack();
		stack.setUrl(url);

	}

	public Stack getStack() {
		return stack;
	}

	public void scan(String url) {
		String test1="";
		String test2="";
		String test3="";
		String test4="";
		
		GatewayServer gatewayServer = new GatewayServer(new ScanningUrl(url));
		gatewayServer.start();
		System.out.println("Gateway Server Stated");
		//C:\Users\JongHyuk\git\JHJ_Scanner\wvsproject\src\main\java\scanner\ss_0_test.py
		try {
			
			String path = "C:/Users/JongHyuk/git/JHJ_Scanner/wvsproject/src/main/java/scanner/ss_0_test.py";
			Process p = Runtime.getRuntime().exec("python " + path);
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream(),"UTF-8"));
			//BufferedReader errin = new BufferedReader(new InputStreamReader(p.getErrorStream(),"UTF-8"));
			
			System.out.println("java in========================");
			String line;
			
			while ((line = in.readLine()) != null) {
				if (line.startsWith("[1]url")){
				test1 = line;	
				}
				if (line.startsWith("[2]formname")){
				test2 = line;	
				}
				if(line.startsWith("[3.1]")){
				test3 = line;
				}
				if(line.startsWith("[3.2]")){
					test4 = line;
				}
			
				/*while ((line = errin.readLine()) != null) {
				System.out.println(line);
					
				}*/
			}
			System.out.println(test1);
			System.out.println(test2);
			System.out.println(test3);
			String[] array1 = test1.split(",");
			String[] array2 = test2.split(",");
			String[] array3 = test3.split("', '");
			String[] array4 = test3.split("', '");

			System.out.println("array1 Testing***************");
			
			for (int i = 0; i < array1.length; i++) {
				System.out.println(array1[i]);
			}
			System.out.println("array3 Testing***************");
			
			for (int i = 0; i < array2.length; i++) {
				System.out.println(array2[i]);
			}
			
			System.out.println("array3 Testing***************");
			
			for (int i = 0; i < array3.length; i++) {
				System.out.println(array3[i]);
			}
			System.out.println("array4 Testing***************");
			
			for (int i = 0; i < array4.length; i++) {
				System.out.println(array4[i]);
			}
			
			System.out.println(array3.length);
			in.close();
			p.waitFor();
			System.out.println("end*");
			//in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}