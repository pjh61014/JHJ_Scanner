package jhj.scanner.bo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bson.types.ObjectId;

import jhj.scanner.dto.formInfoDTO;
import jhj.scanner.dto.scanDTO;
import jhj.scanner.dto.scanInfoDTO;
import jhj.scanner.dto.vulInfoDTO;
import py4j.GatewayServer;

public class ScanningUrl {
	private Stack stack;
	private scanInfoDTO scandto;
	private List<vulInfoDTO> vulinfolist;

	private vulInfoDTO vulinfo;
	private formInfoDTO formdto;
	private scanDTO totalinfo;
	ObjectId id;

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

	public scanDTO scan(String url) {
		
		String regexDomain = "";
		String targetDomain = "";
		String hyphen = "";
		String colon = "";
		String resultid = "";
		String date = "";
		String[] dateSplit;
		String[] hyphenSplit;
		String[] colonSplit;
		String[] postTagids ={};
		String[] postFormnames={};

		vulinfolist = new ArrayList<vulInfoDTO>();

		GatewayServer gatewayServer = new GatewayServer(new ScanningUrl(url));
		gatewayServer.start();
		System.out.println("Gateway Server Stated");

		try {

			System.out.println("**[java] python soure code execute!!");
			String path = "C:\\Users\\JongHyuk\\Downloads\\JHJ_Scanner\\wvsproject\\src\\main\\java\\scanner\\scanall.py";

			Process p = Runtime.getRuntime().exec("cmd /c python " + path);

			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
			System.out.println("[py4j] output reading....");

			String line;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
				if (line.startsWith("urldate")) {
					System.out.println("****[java] url&date reading");
					Date d = new Date();
					ObjectId id = new ObjectId();
					String[] scaninfo = line.split(",");

					for (int i = 0; i < scaninfo.length; i++) {
						scaninfo[i].trim();
						scaninfo[i].replaceAll(" ", "");
						scaninfo[i].replaceAll("(^\\p{Z}+|\\p{Z}+$)", "");
						scaninfo[i].replaceAll("\u00A0", "");
						scaninfo[i].replaceAll("\\s", "");
						System.out.println(i + " ¹øÂ°" + scaninfo[i]);

					}

					Pattern urlPattern = Pattern.compile(
							"^(https?):\\/\\/([^:\\/\\s]+)(:([^\\/]*))?((\\/[^\\s/\\/]+)*)?\\/([^#\\s\\?]*)(\\?([^#\\s]*))?(#(\\w*))?$");

					System.out.println("scaninfo[1]: " + scaninfo[1]);
					targetDomain = scaninfo[1];

					Matcher mc = urlPattern.matcher(targetDomain.trim());
					if (mc.matches()) {
						for (int i = 0; i < mc.groupCount(); i++) {
							System.out.println("group(" + i + ") = " + mc.group(i));
						}

					} else {
						System.out.println("not found");
					}
					regexDomain = mc.group(2);
					date = scaninfo[2];
					dateSplit = date.trim().split(" ");
					hyphenSplit = dateSplit[0].split("-");
					colonSplit = dateSplit[1].split(":");
					for (int i = 0; i < hyphenSplit.length; i++) {
						System.out.println(i + " ¹øÀç" + hyphenSplit[i]);
						hyphen += hyphenSplit[i];
					}
					for (int i = 0; i < colonSplit.length; i++) {
						System.out.println(i + " ¹øÀç" + colonSplit[i]);
						colon += colonSplit[i];
					}
					resultid = hyphen.toString() + colon.toString() + regexDomain;
					System.out.println(resultid);
					scandto = new scanInfoDTO(id.toString(), targetDomain.trim(), date.trim(), resultid, "SQLi");

					System.out.println("***************************************************************");

					System.out.println("url:" + scandto.getUrl());
					System.out.println("***************************************************************");
				}
				if (line.startsWith("formname")) {
					System.out.println("formname ÀÐ¾î¿È");
					String[] formNames = line.split(",");
					ArrayList<String> preFormnames = new ArrayList<String>();

					for (int i = 1; i < formNames.length; i++) {
						preFormnames.add(formNames[i]);

					}

					String[] refineFormnames = preFormnames.toArray(new String[preFormnames.size()]);
					postFormnames = new String[refineFormnames.length];
					String formname;

					for (int i = 0; i < refineFormnames.length; i++) {
						formname = refineFormnames[i];
						postFormnames[i] = formname.trim();
						formname = "";
					}

					for (int i = 0; i < postFormnames.length; i++) {
						System.out.println(postFormnames[i]);
					}

				}

				if (line.startsWith("tagid")) {
					System.out.println("tagid ÀÐ¾î¿È");
					String[] tagIds = line.split(",");
					ArrayList<String> preTagids = new ArrayList<String>();

					for (int i = 1; i < tagIds.length; i++) {
						preTagids.add(tagIds[i]);
					}

					String[] refineTagids = preTagids.toArray(new String[preTagids.size()]);
					postTagids = new String[refineTagids.length];
					String tagid;

					for (int i = 0; i < refineTagids.length; i++) {
						tagid = refineTagids[i];
						postTagids[i] = tagid.trim();
						tagid = "";
					}

					for (int i = 0; i < postTagids.length; i++) {
						System.out.println(postTagids[i]);
					}

				}

				if (line.startsWith("vulinfo")) {
					System.out.println("vulinfo ÀÐ¾î¿È");
					String trim = line.trim();
					String[] vul = line.split(",");
					for (int i = 0; i < vul.length; i++) {
						System.out.print("[" + i + "] " + vul[i] + ",");
						System.out.println("");
					}

					vulinfo = new vulInfoDTO(vul[1], vul[2], vul[3] + vul[4], vul[5]);
					vulinfolist.add(vulinfo);

				}
			}

			System.out.println("**************************form name & tagid************************************");
			formdto = new formInfoDTO(postFormnames, postTagids);
			//System.out.println(formdto.getForm_name());
			//System.out.println(formdto.getTagid());
			System.out.println("***************************************************************");

			System.out.println("***************************************************************");
			int size = vulinfolist.size();
			for (int i = 0; i < size; i++) {
				vulInfoDTO vuldto = vulinfolist.get(i);

				System.out.println("vuldname: " + vuldto.getVul_name() + "pattern_id: " + vuldto.getPatterm_id()
						+ "pattern: " + vuldto.getPattern() + "pattern_id" + vuldto.getPattern_dspt());
			}
			System.out.println("**************************vulinfo*************************************");
			in.close();

			p.waitFor();

			System.out.println("end");

		} catch (IOException e) {
			e.printStackTrace();
		}

		catch (InterruptedException e) {

			e.printStackTrace();
		}

		totalinfo = new scanDTO(scandto, formdto, vulinfolist);

		return totalinfo;

	}

}