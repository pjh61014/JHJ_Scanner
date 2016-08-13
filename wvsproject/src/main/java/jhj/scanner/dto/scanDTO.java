package jhj.scanner.dto;

import java.util.List;

public class scanDTO {

	private formInfoDTO forminfo;
	private scanInfoDTO scaninfo;
	private List<vulInfoDTO> vulinfo;

	public scanDTO() {

	}

	public scanDTO(scanInfoDTO scaninfo, formInfoDTO forminfo, List<vulInfoDTO> vulinfo) {
		super();
		this.forminfo = forminfo;
		this.scaninfo = scaninfo;
		this.vulinfo = vulinfo;
	}

	public formInfoDTO getForminfo() {
		return forminfo;
	}

	public void setForminfo(formInfoDTO forminfo) {
		this.forminfo = forminfo;
	}

	public scanInfoDTO getScaninfo() {
		return scaninfo;
	}

	public void setScaninfo(scanInfoDTO scaninfo) {
		this.scaninfo = scaninfo;
	}

	public List<vulInfoDTO> getVulinfo() {
		return vulinfo;
	}

	public void setVulinfo(List<vulInfoDTO> vulinfo) {
		this.vulinfo = vulinfo;
	}

}
