package jhj.scanner.dto;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "scanresult")
public class formInfoDTO {
	
	@Field
	private String[]form_name;
	@Field
	private String[] tagid;

	public formInfoDTO() {
		super();
	}

	public formInfoDTO(String[] form_name, String[] tagid) {
		super();
		this.form_name = form_name;
		this.tagid = tagid;
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

	

	

}
