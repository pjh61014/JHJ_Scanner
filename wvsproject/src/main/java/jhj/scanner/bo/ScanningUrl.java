package jhj.scanner.bo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

		GatewayServer gatewayServer = new GatewayServer(new ScanningUrl(url));
		gatewayServer.start();
		System.out.println("Gateway Server Stated");

		try {
			System.out.println("**[java] python soure code execute!!");
			String path = "C:\\Users\\Administrator\\git\\JHJ_Scanner\\wvsproject\\src\\main\\java\\scanner\\scanall.py";
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
			System.out.println("endddddd");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// GatewayServer.turnLoggingOn();
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// gatewayServer.shutdown();
		/*
		 * String path =1
		 * "C:\\Users\\Administrator\\git\\JHJ_Scanner\\wvsproject\\src\\main\\java\\scanner\\scanall.py";
		 * String path2 =
		 * "C:\\Users\\Administrator\\Documents\\workspace-sts-3.7.3.RELEASE\\jhjScanner\\src\\scanner\\test.py";
		 * System.out.println("\n..." + path); try { System.out.println(
		 * "python ..."); Runtime r = Runtime.getRuntime(); Process p = r.exec(
		 * "python " + path); System.out.println("python .."); p.waitFor();
		 * BufferedReader reader = new BufferedReader(new
		 * InputStreamReader(p.getInputStream())); String line; while ((line =
		 * reader.readLine()) != null) { System.out.println(line); }
		 * reader.close(); System.out.println("sssss"); System.exit(0); } catch
		 * (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

	}
	/*
	 * public static void main(String[] Args) { String url =
	 * "www.address.com/111"; ScanningUrl sp = new ScanningUrl(url); sp.scan();
	 * String url = "wwwwww"; GatewayServer gatewayServer = new
	 * GatewayServer(new ScanningProcesss(url), 25335); gatewayServer.start();
	 * String path =
	 * "C:\\Users\\JongHyuk\\git\\JHJ_Scanner\\wvsproject\\src\\main\\java\\scanner\\scanall.py";
	 * try { Process p = Runtime.getRuntime().exec("python " + path);
	 * p.waitFor(); BufferedReader reader = new BufferedReader(new
	 * InputStreamReader(p.getInputStream())); String line; while ((line =
	 * reader.readLine()) != null) { System.out.println(line); } } catch
	 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace();
	 * } catch (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * gatewayServer.shutdown();
	 * 
	 * }
	 * 
	 */}