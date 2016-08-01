package jhj.scanner.bo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import py4j.GatewayServer;

public class ScanningProcess {
	private String url;
	private Stack stack;

	public ScanningProcess(){
			
	}

	public ScanningProcess(String url) {
		super();
		stack = new Stack();
		stack.setUrl(url);
		
	}

	public String getUrl() {
		return url;
	}

	public Stack getStack() {
		return stack;
	}
	

	public void scan(String url){
		if (url.length()>0){
			System.out.println("넘겨바은 url==>"+url);
			
			
			try {
				GatewayServer gatewayServer = new GatewayServer(new ScanningProcess(url));
				gatewayServer.start();
				String path ="C:\\Users\\Administrator\\git\\JHJ_Scanner\\wvsproject\\src\\main\\java\\scanner\\scanall.py";
				Runtime r = Runtime.getRuntime();
				Process p = r.exec("python "+path);
				p.waitFor();
				System.out.println("waiting..");
				BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream())); 
				String line; 
				while ((line = reader.readLine()) != null) {
					System.out.println(line); }
				reader.close(); System.out.println("sssss"); System.exit(0); 
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// GatewayServer.turnLoggingOn();
	
			System.out.println("Gateway Server Stated");
		}else{
			System.out.println("잘못됨");
		}
		// gatewayServer.shutdown();
		/*
		 * String path =
		 * "C:\\Users\\Administrator\\git\\JHJ_Scanner\\wvsproject\\src\\main\\java\\scanner\\scanall.py";
		 * String path2 =
		 * "C:\\Users\\Administrator\\Documents\\workspace-sts-3.7.3.RELEASE\\jhjScanner\\src\\scanner\\test.py";
		 * System.out.println("\n..." + path); try { System.out.println(
		 * "python ..."); Runtime r = Runtime.getRuntime(); Process p =
		 * r.exec("python " + path); System.out.println("python...");
		 * p.waitFor(); BufferedReader reader = new BufferedReader(new
		 * InputStreamReader(p.getInputStream())); String line; while ((line =
		 * reader.readLine()) != null) { System.out.println(line); }
		 * reader.close(); System.out.println("sssss"); System.exit(0); } catch
		 * (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

	}

	/*public static void main(String[] Args) {
		String url = "www.address.com/111";
		ScanningProcess sp = new ScanningProcess(url);
		sp.scan();
		
		 * String url = "wwwwww"; GatewayServer gatewayServer = new
		 * GatewayServer(new ScanningProcesss(url), 25335);
		 * gatewayServer.start(); String path =
		 * "C:\\Users\\JongHyuk\\git\\JHJ_Scanner\\wvsproject\\src\\main\\java\\scanner\\scanall.py";
		 * try { Process p = Runtime.getRuntime().exec("python " + path);
		 * p.waitFor(); BufferedReader reader = new BufferedReader(new
		 * InputStreamReader(p.getInputStream())); String line; while ((line =
		 * reader.readLine()) != null) { System.out.println(line); } } catch
		 * (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * 
		 * gatewayServer.shutdown();
		 
	}
*/}
