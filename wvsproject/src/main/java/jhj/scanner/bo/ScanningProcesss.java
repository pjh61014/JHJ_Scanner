package jhj.scanner.bo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import py4j.GatewayServer;

public class ScanningProcesss {
	private String url;
	
		
	public ScanningProcesss() {
		super();
	}

	public ScanningProcesss(String url) {
		super();
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}

	
	public void scan(String url){
		
		GatewayServer gatewayServer = new GatewayServer(new ScanningProcesss(url),25335);
		gatewayServer.start();
		GatewayServer.turnLoggingOn();
		
		
		System.out.println("Gateway Server Stated");
		String path = 
				"C:\\Users\\Administrator\\git\\JHJ_Scanner\\wvsproject\\src\\main\\java\\scanner\\scanall.py";
		String path2="C:\\Users\\Administrator\\Documents\\workspace-sts-3.7.3.RELEASE\\jhjScanner\\src\\scanner\\test.py";
		System.out.println("\n실행중..."+path);
		try {
			System.out.println("python 실행중...");
			Runtime r = Runtime.getRuntime();
			Process p = r.exec("cmd /c python "+path);
			System.out.println("python 실행완료...");
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while((line = reader.readLine()) != null){
				
				System.out.println(line);
			}
			reader.close();
			System.out.println("sssss");
			System.exit(0);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	/*public static void main(String[] Args){
		
		String path = 
				"C:\\Users\\Administrator\\Documents\\workspace-sts-3.7.3.RELEASE\\jhjScanner\\src\\scanner\\test.py";
		try {
			Process p = Runtime.getRuntime().exec("cmd /c python "+path);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while((line = reader.readLine()) != null){
				System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
*/
}
