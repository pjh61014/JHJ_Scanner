package jhj.scanner.dto;

public class scanInfoDTO {

	private String code;
	private String url;
	private String date;

	public scanInfoDTO() {
		super();

	}
	
	

	public scanInfoDTO(String url, String date, String code) {
		super();
		this.url = url;
		this.date = date;
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

}
