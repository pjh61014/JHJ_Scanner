package jhj.scanner.dto;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "scanresult")
public class vulInfoDTO {
	@Field
	private String vul_name;
	@Field
	private String patterm_id;
	@Field
	private String pattern;
	@Field
	private String pattern_dspt;

	public vulInfoDTO() {
		super();
	}

	public vulInfoDTO(String vul_name, String patterm_id, String pattern, String pattern_dspt) {
		super();
		this.vul_name = vul_name;
		this.patterm_id = patterm_id;
		this.pattern = pattern;
		this.pattern_dspt = pattern_dspt;
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

}
