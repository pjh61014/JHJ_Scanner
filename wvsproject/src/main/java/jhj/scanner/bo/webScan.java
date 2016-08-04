package jhj.scanner.bo;

import java.util.ArrayList;

import jhj.scanner.dto.formInfoDTO;
import jhj.scanner.dto.vulInfoDTO;

/*
 * 
 * 1.������ 
 * 2.�Ͻ�
 * 3.�߻������
 * 	 -�������
 * 	 -����� ���ϸ�
 *   -�������� or ��ũ��Ʈ
 *   -����� ���� ��� ����
 *   -�÷���
 * 4.������(�����ϴ� ���)
 * 	 -�� Ÿ��
 *   -����
 *   -�±׾��̵� ��
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
