package jhj.scanner.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "scanresult")
public class formInfoDTO {
	@Id
	String _id;
	private String form_name;
	private String tagid;

	public formInfoDTO() {
		super();
	}

	public formInfoDTO(String form_name, String tagid,String _id) {
		super();
		this._id = _id;
		this.form_name = form_name;
		this.tagid = tagid;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
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

	@Override
	public String toString() {
		return "formInfoDTO [_id=" + _id + ", form_name=" + form_name + ", tagid=" + tagid + "]";
	}
	
	

}
