package jhj.scanner.dto;

import java.util.Arrays;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "scanresult")
public class formInfoDTO {
	
	@Field
	private String[]form_name;
	@Field
	private String[] tagid;
	@Field
	private String resultid;

	public formInfoDTO() {
		super();
	}

	public formInfoDTO(String[] form_name, String[] tagid) {
		super();
		this.form_name = form_name;
		this.tagid = tagid;
	}
	
	

	public formInfoDTO(String[] form_name, String[] tagid, String resultid) {
		super();
		this.form_name = form_name;
		this.tagid = tagid;
		this.resultid = resultid;
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

	public String getResultid() {
		return resultid;
	}

	public void setResultid(String resultid) {
		this.resultid = resultid;
	}

	@Override
	public String toString() {
		return "formInfoDTO [form_name=" + Arrays.toString(form_name) + ", tagid=" + Arrays.toString(tagid)
				+ ", resultid=" + resultid + "]";
	}

	
	

	

}
