package jhj.scanner.bo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import py4j.GatewayServer;

public class EntryPointUrl {

	private webScan webscan;

	public EntryPointUrl() {
		
	}

	public EntryPointUrl(String url) {
		super();
		webscan = new webScan();
		webscan.setUrl(url);
		System.out.println(webscan.getURL());

	}

	public webScan getwebScan() {
		return webscan;
	}

	public void scan(String url) {

		GatewayServer gatewayServer = new GatewayServer(new EntryPointUrl(url));
		gatewayServer.start();
		System.out.println("Gateway Server Stated");

		try {
			System.out.println("**[java] python soure code execute!!");
			String path = "C:\\Users\\Administrator\\git\\JHJ_Scanner\\wvsproject\\src\\main\\java\\scanner\\scanall2.py";
			
			Process p = Runtime.getRuntime().exec("cmd /c python " + path);
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			System.out.println("[java in========================");
			System.out.println(in);
			String line;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
			in.close();
			p.waitFor();
			System.out.println("end");
		} catch (IOException e) {
			e.printStackTrace();
		}

		catch (InterruptedException e) {

			e.printStackTrace();
		}

	}
}