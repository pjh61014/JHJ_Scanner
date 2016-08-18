package jhj.scanner.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="test_a")
public class testDTO {
	
	@Id
	String _id;
	private String url;
	private String date;
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
	
	public testDTO(){}
	
	public testDTO(String _id, String url, String date) {
		super();
		this._id = _id;
		this.url = url;
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "testDTO [_id=" + _id + ", url=" + url + ", date=" + date + "]";
	}
	

}
