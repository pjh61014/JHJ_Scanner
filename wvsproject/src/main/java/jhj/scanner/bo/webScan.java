package jhj.scanner.bo;

import java.util.ArrayList;

import jhj.scanner.dto.formInfoDTO;
import jhj.scanner.dto.vulInfoDTO;

/*
 * 
 * 1.도메인 
 * 2.일시
 * 3.발생취약점
 * 	 -취약점명
 * 	 -취약점 패턴명
 *   -공격쿼리 or 스크립트
 *   -취약점 패턴 요약 설명
 *   -플래그
 * 4.폼정보(공격하는 대상)
 * 	 -폼 타입
 *   -폼명
 *   -태그아이디 명
 *  
 * 
 * */

public class webScan {

	private String url;
	private String date;
	ArrayList<vulInfoDTO> vullist = new ArrayList<vulInfoDTO>();
	ArrayList<formInfoDTO> formlist = new ArrayList<formInfoDTO>();

	public void setUrl(String url) {
		this.url = url;
	}

	public String getURL() {

		return this.url;

	}

	public void date(String date) {
		this.date = date;
	}

	public void vulResult(String vul_name, String pattern_id, String pattern, String pattern_dspt, String vulTag) {
		vulInfoDTO vuldto = new vulInfoDTO(vul_name, pattern_id, pattern, pattern_dspt, vulTag);
		vullist.add(vuldto);

	}

}
