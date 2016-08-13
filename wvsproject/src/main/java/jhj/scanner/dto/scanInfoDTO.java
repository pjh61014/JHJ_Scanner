package jhj.scanner.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "scanresult")
public class scanInfoDTO {
	@Id
	String _id;
	private String url;
	private String date;
	private String form_name;
	private String tagid;

	public scanInfoDTO() {
		super();

	}

	public scanInfoDTO(String url, String date, String _id) {
		super();
		this._id = _id;
		this.url = url;
		this.date = date;
	}

	
	
	public scanInfoDTO(String _id, String url, String date, String form_name) {
		super();
		this._id = _id;
		this.url = url;
		this.date = date;
		this.form_name = form_name;
	}
	
	

	public scanInfoDTO(String _id, String url, String date, String form_name, String tagid) {
		super();
		this._id = _id;
		this.url = url;
		this.date = date;
		this.form_name = form_name;
		this.tagid = tagid;
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

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		System.out.println("toString()");
		return "scanInfoDTO [_id=" + _id + ", url=" + url + ", date=" + date + "]";
	}
	
	

}
