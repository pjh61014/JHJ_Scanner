package jhj.scanner.bo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.mongodb.util.JSON;

import py4j.GatewayServer;

public class ScanningUrl {

	private Stack stack;
	private JSON json;

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

		GatewayServer gatewayServer = new GatewayServer(new ScanningUrl(url));
		gatewayServer.start();
		System.out.println("Gateway Server Stated");

		try {
			System.out.println("**[java] python soure code execute!!");
			String path = "C:/Users/JongHyuk/git/JHJ_Scanner/wvsproject/src/main/java/scanner/scanall.py";
			Process p = Runtime.getRuntime().exec("cmd /c python " + path);
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			System.out.println("[java in========================");
			//System.out.println(in);
			String line;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
			//in.close();
			p.waitFor();
			System.out.println("end*");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// GatewayServer.turnLoggingOn();
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	}