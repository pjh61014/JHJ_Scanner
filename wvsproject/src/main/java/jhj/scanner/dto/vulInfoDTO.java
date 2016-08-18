package jhj.scanner.dto;

import java.util.Arrays;

public class vulInfoDTO {

	private String code;
	private String[] vul_name;
	private String[] vul_pattern;

	public vulInfoDTO() {
	}

	public vulInfoDTO(String code, String[] vul_name, String[] vul_pattern) {
		super();
		this.code = code;
		this.vul_name = vul_name;
		this.vul_pattern = vul_pattern;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String[] getVul_name() {
		return vul_name;
	}

	public void setVul_name(String[] vul_name) {
		this.vul_name = vul_name;
	}

	public String[] getVul_pattern() {
		return vul_pattern;
	}

	public void setVul_pattern(String[] vul_pattern) {
		this.vul_pattern = vul_pattern;
	}

	@Override
	public String toString() {
		return "vulInfoDTO [code=" + code + ", vul_name=" + Arrays.toString(vul_name) + ", vul_pattern="
				+ Arrays.toString(vul_pattern) + "]";
	}

}
