package jhj.scanner.dto;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "scanresult")
public class scanInfoDTO {
	@Id
	private String _id;

	@Field
	private String url;
	@Field
	private String date;
	@Field
	private String code;
	@Field
	private String form_name;
	@Field
	private String tagid;
	@Field
	private formInfoDTO forminfo;
	@Field
	private List<vulInfoDTO> vulinfo;

	public scanInfoDTO() {
		super();

	}

	public scanInfoDTO(String url, String date) {
		super();
		this.url = url;
		this.date = date;
	}

	public scanInfoDTO(String url, String date, String _id, String code) {
		super();
		this._id = _id;
		this.url = url;
		this.date = date;
		this.code = code;
	}

	/*
	 * public scanInfoDTO(String _id, String url, String date, String form_name)
	 * { super(); this._id = _id; this.url = url; this.date = date;
	 * this.form_name = form_name; }
	 */

	/*
	 * public scanInfoDTO(String _id, String url, String date, String form_name,
	 * String tagid) { super(); this._id = _id; this.url = url; this.date =
	 * date; this.form_name = form_name; this.tagid = tagid; }
	 */

	public String getForm_name() {
		return form_name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public formInfoDTO getForminfo() {
		return forminfo;
	}

	public void setForminfodto(formInfoDTO forminfo) {
		this.forminfo = forminfo;
	}

	public List<vulInfoDTO> getVulinfo() {
		return vulinfo;
	}

	public void setVulinfo(List<vulInfoDTO> vulinfo) {
		this.vulinfo = vulinfo;
	}

	@Override
	public String toString() {
		System.out.println("toString()");
		return "scanInfoDTO [_id=" + _id + ", url=" + url + ", date=" + date + "]";
	}

}
