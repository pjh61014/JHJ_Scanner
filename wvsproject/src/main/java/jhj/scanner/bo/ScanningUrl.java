package jhj.scanner.bo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import py4j.GatewayServer;

public class ScanningUrl {

	private Stack stack;
	private String date;

	public ScanningUrl() {

	}

	public ScanningUrl(String url) {
		super();
		stack = new Stack();
		stack.setUrl(url);

	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;

	}

	public Stack getStack() {
		// System.out.println("sssssssssssssssssssssssss" + stack.toString());
		return stack;
	}

	public String scan(String url) {

		GatewayServer gatewayServer = new GatewayServer(new ScanningUrl(url));
		gatewayServer.start();
		System.out.println("Gateway Server Stated");
		// System.out.println("dfgdgdfgdgfdfgdfgdf00000000000000000000"+getStack());
		try {
			// System.out.println("dfgdgdfgdgfdfgdfgdf++++++++++++++++"+getStack());
			System.out.println("**[java] python soure code execute!!");
			String path = "C:\\Users\\Administrator\\git\\JHJ_Scanner\\wvsproject\\src\\main\\java\\scanner\\scanall.py";
			// System.out.println("dfgdgdfgdgfdfgdfgdf111111111111111"+getStack());
			Process p = Runtime.getRuntime().exec("cmd /c python " + path);

			System.out.println("dfgdgdfgdgfdfgdfgd222222222222222f");

			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
			System.out.println("[java in========================");
			// System.out.println(in);
			String line;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
				if (line.startsWith("urldate")) {
					System.out.println("urldate ÀÐ¾î¿È");
					String[] data = line.split(",");

					for (int i = 0; i < data.length; i++) {
						data[i].trim();
						data[i].replaceAll(" ", "");
						System.out.println(data[i+1].toString());

					}

					for (String cha : data) {
						cha.replaceAll(" ", "");
						cha.trim();
						System.out.println(cha);
					}

					String trim = line.trim();
					System.out.println(trim);

					System.out.println(data);

					// ResultDTO dto = new ResultDTO(data[1],data[2]....)
					// list.add(dto)

				}
				if (line.startsWith("formnameid")) {
					System.out.println("formnameid ÀÐ¾î¿È");
					String trim = line.trim();
					System.out.println("trim" + trim);
					String[] data = line.split(",");
					System.out.println(data);

					// ResultDTO dto = new ResultDTO(data[1],data[2]....)
					// list.add(dto)

				}

				if (line.startsWith("vulinfo")) {
					System.out.println("vulinfo ÀÐ¾î¿È");
					String trim = line.trim();
					System.out.println(trim);
					String[] data = line.split(",");
					System.out.println(data);

					// ResultDTO dto = new ResultDTO(data[1],data[2]....)
					// list.add(dto)

				}
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
		return this.date;
	}
}