package jhj.scanner.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="test")
public class scanInfoDTO {
	@Id
	private String url;
	private String date;
	private formInfoDTO formInfo;
	private vulInfoDTO vulInfo;


	public scanInfoDTO() {
		super();

	}

	
	public scanInfoDTO(String url, String date, formInfoDTO formInfo, vulInfoDTO vulInfo) {
		super();
		this.url = url;
		this.date = date;
		this.formInfo = formInfo;
		this.vulInfo = vulInfo;
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


	public formInfoDTO getFormInfo() {
		return formInfo;
	}


	public void setFormInfo(formInfoDTO formInfo) {
		this.formInfo = formInfo;
	}


	public vulInfoDTO getVulInfo() {
		return vulInfo;
	}


	public void setVulInfo(vulInfoDTO vulInfo) {
		this.vulInfo = vulInfo;
	}


	@Override
	public String toString() {
		return "scanInfoDTO [url=" + url + ", date=" + date + ", formInfo=" + formInfo + ", vulInfo=" + vulInfo + "]";
	}


}
