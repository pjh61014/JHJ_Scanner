package jhj.scanner.dto;

public class scanInfoDTO {
	private String url;
	private String date;

	public scanInfoDTO() {
		super();

	}

	public scanInfoDTO(String url, String date) {
		super();
		this.url = url;
		this.date = date;
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

}
