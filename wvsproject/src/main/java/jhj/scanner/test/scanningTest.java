package jhj.scanner.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

		String testurl = "http://demo.testfire.net/bank/login.aspx";
		String date = " 2016-08-28 14:43:49";
		String hyphen = "";
		String colon = "";
		String[] datesplit;
		String[] hyphensplit;
		String[] conlonsplit;
		String resultid;
		datesplit = date.trim().split(" ");
		System.out.println(datesplit.length);
		for (int i = 0; i < datesplit.length; i++) {
			System.out.println(i + " 번재" + datesplit[i]);
		}
		hyphensplit = datesplit[0].split("-");
		conlonsplit = datesplit[1].split(":");
		System.out.println(hyphensplit.length);
		System.out.println(conlonsplit.length);
		for (int i = 0; i < hyphensplit.length; i++) {
			System.out.println(i + " 번재" + hyphensplit[i]);
			hyphen += hyphensplit[i];
		}
		for (int i = 0; i < conlonsplit.length; i++) {
			System.out.println(i + " 번재" + conlonsplit[i]);
			colon += conlonsplit[i];
		}
		System.out.println("hyphen: " + hyphen.toString() + " colon: " + colon.toString());

		Pattern urlPattern = Pattern.compile(
				"^(https?):\\/\\/([^:\\/\\s]+)(:([^\\/]*))?((\\/[^\\s/\\/]+)*)?\\/([^#\\s\\?]*)(\\?([^#\\s]*))?(#(\\w*))?$");
		Matcher mc = urlPattern.matcher(testurl);
		if (mc.matches()) {
			for (int i = 0; i < mc.groupCount(); i++) {
				System.out.println("group(" + i + ") = " + mc.group(i));
			}

		}else{
			System.out.println("not found");
		}
		System.out.println(mc.group(2));
		String domain=mc.group(2);
		System.out.println("total: "+hyphen.toString()+colon.toString()+domain);
		resultid = hyphen.toString()+colon.toString()+domain;
		System.out.println(resultid);	
		/*
		 * String url = "wwww.navefser.com"; GatewayServer gatewayServer = new
		 * GatewayServer(new scanningTest(url)); gatewayServer.start();
		 * System.out.println("Gateway Server Started"); String path =
		 * "C:/Users/Administrator/Documents/workspace-sts-3.7.3.RELEASE/jhjScanner/src/scanner/test.py";
		 * 
		 * System.out.println("\n실행중..."+path); try { System.out.println(
		 * "python 실행중..."); Runtime r = Runtime.getRuntime(); Process p =
		 * r.exec("cmd /c python "+path); System.out.println("python 실행완료...");
		 * 
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * gatewayServer.shutdown();
		 */

	}

}
