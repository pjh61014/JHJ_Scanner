package jhj.scanner.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "score")
public class scanDTO {
	@Id
	String _id;
	private String url;
	private String date;
	private String vul_name;
	private String patterm_id;
	private String pattern;
	private String pattern_dspt;
	private String form_name;
	private String tagid;

	public scanDTO() {

	}

	public scanDTO(String _id, String url, String date, String vul_name, String patterm_id, String pattern,
			String pattern_dspt, String form_name, String tagid) {
		super();
		this._id = _id;
		this.url = url;
		this.date = date;
		this.vul_name = vul_name;
		this.patterm_id = patterm_id;
		this.pattern = pattern;
		this.pattern_dspt = pattern_dspt;
		this.form_name = form_name;
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

	public String getVul_name() {
		return vul_name;
	}

	public void setVul_name(String vul_name) {
		this.vul_name = vul_name;
	}

	public String getPatterm_id() {
		return patterm_id;
	}

	public void setPatterm_id(String patterm_id) {
		this.patterm_id = patterm_id;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getPattern_dspt() {
		return pattern_dspt;
	}

	public void setPattern_dspt(String pattern_dspt) {
		this.pattern_dspt = pattern_dspt;
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
		return "scanDTO [_id=" + _id + ", url=" + url + ", date=" + date + ", vul_name=" + vul_name + ", patterm_id="
				+ patterm_id + ", pattern=" + pattern + ", pattern_dspt=" + pattern_dspt + ", form_name=" + form_name
				+ ", tagid=" + tagid + "]";
	}
	
	

}
