package jhj.scanner.dto;

import java.util.Arrays;

public class formInfoDTO {

	private String form_type;
	private String[] form_name;
	private String[] tagid;

	public formInfoDTO() {
		super();
	}

	public formInfoDTO(String form_type, String[] form_name, String[] tagid) {
		super();
		this.form_type = form_type;
		this.form_name = form_name;
		this.tagid = tagid;
	}

	public String getForm_type() {
		return form_type;
	}

	public void setForm_type(String form_type) {
		this.form_type = form_type;
	}

	public String[] getForm_name() {
		return form_name;
	}

	public void setForm_name(String[] form_name) {
		this.form_name = form_name;
	}

	public String[] getTagid() {
		return tagid;
	}

	public void setTagid(String[] tagid) {
		this.tagid = tagid;
	}

	@Override
	public String toString() {
		return "formInfoDTO [form_type=" + form_type + ", form_name=" + Arrays.toString(form_name) + ", tagid="
				+ Arrays.toString(tagid) + "]";
	}
	
	

}
