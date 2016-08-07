package jhj.scanner.bo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.tagext.TagInfo;
import javax.tools.ForwardingJavaFileManager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.python.antlr.ast.arguments;

import jhj.scanner.dto.formInfoDTO;
import jhj.scanner.dto.scanInfoDTO;
import jhj.scanner.dto.vulInfoDTO;
import py4j.GatewayServer;

public class ScanningUrl {

	private Stack stack;
	private String date;
	private scanInfoDTO scandto;
	private List<vulInfoDTO> vulinfolist;
	private vulInfoDTO vulinfo;
	private formInfoDTO formdto;
	JSONObject obj;
	JSONArray list;
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

	public JSONObject scan(String url) {
		String formnames = "";
		String tagId = "";
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
					String[] scaninfo = line.split(",");

					for (int i = 0; i < scaninfo.length; i++) {
						scaninfo[i].trim();
						scaninfo[i].replaceAll(" ", "");
						//System.out.println(scaninfo[i]);

					}
					
					scandto = new scanInfoDTO(scaninfo[1], scaninfo[2], "jh");
					obj = new JSONObject();
					obj.put("url", scaninfo[1]);
					obj.put("date", scaninfo[2]);
					//System.out.println("***************************************************************");
					//System.out.println("date:" + scandto.getDate());
					//System.out.println("url:" + scandto.getUrl());
					//System.out.println("***************************************************************");
					// ResultDTO dto = new ResultDTO(data[1],data[2]....)
					// list.add(dto)

				}
				if (line.startsWith("formname")) {
					System.out.println("formname ÀÐ¾î¿È");
					String[] formname = line.split(",");
					list= new JSONArray();
					for (int i = 1; i < formname.length; i++) {
						formname[i].trim();
						formname[i].replaceAll(" ", "");
						formnames = formnames + formname[i];
						list.add(formname[i]);
						//System.out.println(formname[i]);
						//System.out.println("formnames" + formnames);
					}
					//System.out.println("***************************************************************");
					// ResultDTO dto = new ResultDTO(data[1],data[2]....)
					// list.add(dto)

					obj.put("formname", list);
					
					System.out.println("ddddsaddddddddddddddddddd");
					System.out.println(obj.toJSONString());
				}

				if (line.startsWith("tagid")) {
					System.out.println("tagid ÀÐ¾î¿È");
					String[] tagid = line.split(",");
					List<String> text = new ArrayList<String>();
					String[] tagidlist = new String[text.size()];
					list=new JSONArray();

					for (int i = 1; i < tagid.length; i++) {
						tagid[i].trim();
						tagid[i].replaceAll(" ", "");
						list.add(tagid[i]);
						text.add(tagid[i]);

					}
					text.toArray(tagidlist);

					//System.out.println(text.toString());
					tagId = text.toString();
					obj.put("tagid", list);
					System.out.println("ddddsaddddddddddddddddddd");
					System.out.println(obj.toJSONString());
					//System.out.println(tagId);

				}
				//System.out.println("***************************************************************");
				formdto = new formInfoDTO(formnames, tagId, "jhj");
				//System.out.println(formdto.getForm_name());
				//System.out.println(formdto.getTagid());
				//System.out.println("***************************************************************");
				vulinfolist = new ArrayList<vulInfoDTO>();
				if (line.startsWith("vulinfo")) {
					System.out.println("vulinfo ÀÐ¾î¿È");
					String trim = line.trim();
					String[] vul = line.split(",");
					list = new JSONArray();
					System.out.println(vul.length);
					
					
					list.add(vul[1]);
					list.add(vul[2]);
					list.add(vul[3]+vul[4]);
					list.add(vul[5]);
					
					obj.put("vulinfo", list);
					System.out.println(obj.toJSONString());
					vulinfo = new vulInfoDTO(vul[1],vul[2],vul[3]+vul[4],vul[5],"jhj");
					vulinfolist.add(vulinfo);
					
					for (int i = 0; i < vulinfolist.size(); i++) {
						System.out.println("ddd"+vulinfolist.get(i).getPattern());
					}

					
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
		return obj;
	}
	
}