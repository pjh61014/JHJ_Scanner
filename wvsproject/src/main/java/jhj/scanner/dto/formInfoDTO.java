package jhj.scanner.dto;

import java.util.Arrays;

public class formInfoDTO {

	
	private String form_name;
	private String tagid;
	private String code;

	public formInfoDTO() {
		super();
	}
	
	

	public formInfoDTO(String form_name, String tagid, String code) {
		super();
		this.form_name = form_name;
		this.tagid = tagid;
		this.code = code;
	}



	public String getForm_name() {
		return form_name;
	}

	public void setForm_name(String form_name) {
		this.form_name = form_name;
	}

	public String getTagid() {
		return tagid;
	}

	public void setTagid(String tagid) {
		this.tagid = tagid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
		
	

}
