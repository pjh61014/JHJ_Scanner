package jhj.scanner.bo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import com.mongodb.util.JSON;

import jhj.scanner.dto.formInfoDTO;
import jhj.scanner.dto.scanInfoDTO;
import jhj.scanner.dto.vulInfoDTO;
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

	public scanInfoDTO scan(String url) {
		String test1="";
		String test2="";
		String test3="";
		String test4="";
		scanInfoDTO scandto = new scanInfoDTO();
		formInfoDTO formdto = new formInfoDTO();
		vulInfoDTO vuldto = new vulInfoDTO();
		
		
		GatewayServer gatewayServer = new GatewayServer(new ScanningUrl(url));
		gatewayServer.start();
		System.out.println("Gateway Server Stated");
		//C:\Users\jeopa\git\JHJ_Scanner\wvsproject\src\main\java\scanner\ss_0.py
		try {
			
			String path = "C:\\Users\\jeopa\\git\\JHJ_Scanner\\wvsproject\\src\\main\\java\\scanner\\ss_0_test.py";
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
				
				System.out.println(line);
			
/*				while ((line = errin.readLine()) != null) {
				System.out.println(line);
					
				}*/
			}
			
			
			System.out.println(test1);
			System.out.println(test2);
			System.out.println(test3);
			System.out.println(test4);
			String[] array1 = test1.split(",");
			String[] array2 = test2.split(",");
			String[] array3 = test3.split("', '");
			String[] array4 = test4.split("', '");

			System.out.println("array1 Testing***************");
			

			for (int i = 0; i < array1.length; i++) {
				System.out.println(array1[i]);
			}
			scandto.setUrl(array1[1]);
			scandto.setDate(array1[2]);
			
			System.out.println("array3 Testing***************");
			
			for (int i = 0; i < array2.length; i++) {
				System.out.println(array2[i]);

			}
			
			formdto.setForm_type("XSS");
			formdto.setForm_name(array2);
			formdto.settag_id(array2);
			
			System.out.println("array3 Testing***************");
			for (int i = 0; i < array3.length; i++) {
				System.out.println(array3[i]);

			}

			System.out.println("array4 Testing***************");

			vuldto.setCode("a");
			vuldto.setVul_pattern(array3);
			vuldto.setVul_name(array4);

			
			for (int i = 0; i < array4.length; i++) {
				System.out.println(array4[i]);
			}
		
			//System.out.println(array3.length);
			in.close();
			p.waitFor();
			System.out.println("end*");
			//in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		scandto.setFormInfo(formdto);
		scandto.setVulInfo(vuldto);
	

		
		
		return scandto;	
	}
}