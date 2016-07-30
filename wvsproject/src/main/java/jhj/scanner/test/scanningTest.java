package jhj.scanner.test;


import java.io.IOException;

import py4j.GatewayServer;

public class scanningTest {
	private String url;

	public scanningTest() {
		System.out.println("dfasfs");
	}

	public scanningTest(String url) {
		this.url = url;
	}

	public String geturl() {
		return url;
	}

	public static void main(String[] args) {
		String url = "wwww.navefser.com";
		GatewayServer gatewayServer = new GatewayServer(new scanningTest(url));
		gatewayServer.start();
		System.out.println("Gateway Server Started");
		String path = 
				"C:/Users/Administrator/Documents/workspace-sts-3.7.3.RELEASE/jhjScanner/src/scanner/test.py";
		
		System.out.println("\n실행중..."+path);
		try {
			System.out.println("python 실행중...");
			Runtime r = Runtime.getRuntime();
			Process p = r.exec("cmd /c python "+path);
			System.out.println("python 실행완료...");
					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		gatewayServer.shutdown();
	}

}
