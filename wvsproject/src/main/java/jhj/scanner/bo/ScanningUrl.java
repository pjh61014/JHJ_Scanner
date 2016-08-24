package jhj.scanner.bo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

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
	// JSONObject obj;
	// JSONArray list;
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
		String formnames = "";
		String tagId = "";
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

					}
					
					scandto = new scanInfoDTO(scaninfo[1], scaninfo[2], id.toString(),"sqlinjection");
					//scandto = new scanInfoDTO(scaninfo[1], scaninfo[2]);
					//scandto = new scanInfoDTO(scaninfo[1], scaninfo[2],id);
					System.out.println("***************************************************************");
					
					System.out.println("url:" + scandto.getUrl());
					System.out.println("***************************************************************");

					// obj = new JSONObject();
					// obj.put("url", scaninfo[1]);
					// obj.put("date", scaninfo[2]);
					// ResultDTO dto = new ResultDTO(data[1],data[2]....)
					// list.add(dto)

				}
				if (line.startsWith("formname")) {
					System.out.println("formname ÀÐ¾î¿È");
					String[] formname = line.split(",");
					// list= new JSONArray();
					for (int i = 1; i < formname.length; i++) {
						formname[i].trim();
						formname[i].replaceAll(" ", "");
						formname[i].replaceAll("(^\\p{Z}+|\\p{Z}+$)", "");
						formnames = formnames + formname[i];
						// list.add(formname[i]);
						// System.out.println(formname[i]);
						// System.out.println("formnames" + formnames);
					}
					// System.out.println("***************************************************************");
					// ResultDTO dto = new ResultDTO(data[1],data[2]....)
					// list.add(dto)

					// obj.put("formname", list);

					// System.out.println("ddddsaddddddddddddddddddd");
					// System.out.println(obj.toJSONString());
				}

				if (line.startsWith("tagid")) {
					System.out.println("tagid ÀÐ¾î¿È");
					String[] tagid = line.split(",");
					List<String> text = new ArrayList<String>();
					String[] tagidlist = new String[text.size()];
					// list=new JSONArray();

					for (int i = 1; i < tagid.length; i++) {
						tagid[i].trim();
						tagid[i].replaceAll(" ", "");
						tagid[i].replaceAll("(^\\p{Z}+|\\p{Z}+$)", "");
						// list.add(tagid[i]);
						text.add(tagid[i]);

					}
					text.toArray(tagidlist);

					// System.out.println(text.toString());
					tagId = text.toString();
					// obj.put("tagid", list);

					// System.out.println(obj.toJSONString());
					// System.out.println(tagId);

				}
				/*
				 * System.out.println(
				 * "***************************************************************"
				 * ); formdto = new formInfoDTO(formnames, tagId, "jhj");
				 * System.out.println(formdto.getForm_name());
				 * System.out.println(formdto.getTagid()); System.out.println(
				 * "***************************************************************"
				 * );
				 */

				if (line.startsWith("vulinfo")) {
					System.out.println("vulinfo ÀÐ¾î¿È");
					String trim = line.trim();
					String[] vul = line.split(",");
					// list = new JSONArray();
					for (int i = 0; i < vul.length; i++) {
						System.out.print("[" + i + "] " + vul[i] + ",");
						System.out.println("");
					}

					// list.add(vul[1]);
					// list.add(vul[2]);
					// list.add(vul[3]+vul[4]);
					// list.add(vul[5]);

					// obj.put("vulinfo", list);
					// System.out.println(obj.toJSONString());
					ObjectId id = new ObjectId();
					vulinfo = new vulInfoDTO(vul[1], vul[2], vul[3] + vul[4], vul[5]);
					vulinfolist.add(vulinfo);

					// ResultDTO dto = new ResultDTO(data[1],data[2]....)
					// list.add(dto)

				}
			}

			System.out.println("**************************form name & tagid************************************");
			formdto = new formInfoDTO(formnames, tagId);
			System.out.println(formdto.getForm_name());
			System.out.println(formdto.getTagid());
			System.out.println("***************************************************************");

			System.out.println("***************************************************************");
			int size = vulinfolist.size();
			for (int i = 0; i < size; i++) {
				vulInfoDTO vuldto = vulinfolist.get(i);
				// System.out.println(vuldto.getPattern_dspt());
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

	totalinfo = new scanDTO(scandto,formdto,vulinfolist);
	
	return totalinfo;

	}

}