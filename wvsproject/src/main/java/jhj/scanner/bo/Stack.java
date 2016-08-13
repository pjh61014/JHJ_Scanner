package jhj.scanner.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jhj.scanner.dto.vulInfoDTO;

public class Stack {

	private String url;
	private String date;
	ArrayList<vulInfoDTO> vullist = new ArrayList<vulInfoDTO>();
	// ArrayList<formInfoDTO> formlist = new ArrayList<formInfoDTO>();
	Map<String, List<String>> formlist = new HashMap<String, List<String>>();
	ArrayList<String> formnamelist = new ArrayList<String>();
	ArrayList<String> formtypelist = new ArrayList<String>();
	ArrayList<String> taglist = new ArrayList<String>();

	public void target_date(String url, String date) {
		this.date = date;
		this.url = url;
		System.out.println("url: "+url+" date: "+date);
	}

	/*public void vulResult(String vul_name, String pattern_id, String pattern, String pattern_dspt, String vulTag) {
		vulInfoDTO vuldto = new vulInfoDTO(vul_name, pattern_id, pattern, pattern_dspt, vulTag);
		vullist.add(vuldto);
		int size = vullist.size();
		System.out.println(size);
		System.out.println("vulResult 호출");
		for (int i = 0; i < size; i++) {
			vulInfoDTO list = vullist.get(i);
			System.out.println("vul_name : " + list.getVul_name() + "Patterm_id : " + list.getPatterm_id()
					+ "Pattern : " + list.getPattern() + "Pattern_dspt : " + list.getPattern_dspt() + "vultag : "
					+ list.get_id());
		}

	}*/

	public void search_formname(String type, String element) {

		if (type.length() != 0 || element.length() != 0) {

			if (type.equals("formname")) {
				System.out.println("formname " + element);

				formnamelist.add(element);

			} else if (type.equals("tagid")) {
				taglist.add(element);
			} else if (type.equals("formtype")) {
				formtypelist.add(element);
			}

		}

	}

	public void saveFormInfo() {
		System.out.println("호출 안되는거냐???");
		formlist.put("form_name", formnamelist);
		formlist.put("tagid", taglist);
		formlist.put("formtype", formtypelist);
		System.out.println("**********************************************");
		System.out.println("saveFormInfo formname " + formlist.get("form_name"));
		System.out.println("saveFormInfo tagid " + formlist.get("tagid"));
	}
	
	public void today(){
		ScanningUrl test = new ScanningUrl();
		
	}

	/*
	 * public void formResult(String form_type, String[] form_name,String
	 * tagid){ formInfoDTO formdto = new formInfoDTO(form_type, form_name,
	 * tagid); formlist.add(formdto); int size=formlist.size();
	 * System.out.println("************* formresult 호출**************"); for (int
	 * i = 0; i < size; i++) { formInfoDTO list = formlist.get(i);
	 * System.out.println("vul_name : "+list.getVul_name()+"Patterm_id : "
	 * +list.getPatterm_id()+"Pattern : "+list.getPattern()+"Pattern_dspt : "
	 * +list.getPattern_dspt()+ "vultag : "+list.getVulTag()); } }
	 */

	public void test() {
		System.out.println("test()호출");
		for (int i = 0; i < vullist.size(); i++) {
			System.out.println(vullist.get(i).getVul_name() + vullist.get(i).getPattern());
		}

	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return this.url;
	}

}
