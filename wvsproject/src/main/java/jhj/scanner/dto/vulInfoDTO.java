package jhj.scanner.dto;

public class vulInfoDTO {

	private String code;
	private String vul_name;
	private String patterm_id;
	private String pattern;
	private String pattern_dspt;

	public vulInfoDTO() {
	}

	public vulInfoDTO(String code, String vul_name, String patterm_id, String pattern, String pattern_dspt) {
		super();
		this.code = code;
		this.vul_name = vul_name;
		this.patterm_id = patterm_id;
		this.pattern = pattern;
		this.pattern_dspt = pattern_dspt;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	@Override
	public String toString() {
		return "vulInfoDTO [code=" + code + ", vul_name=" + vul_name + ", patterm_id=" + patterm_id + ", pattern="
				+ pattern + ", pattern_dspt=" + pattern_dspt + "]";
	}

}