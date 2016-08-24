package jhj.scanner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jhj.scanner.bo.ScanningUrl;
//github.com/pjh61014/JHJ_Scanner.git
import jhj.scanner.dao.ScannerDAO;
import jhj.scanner.dto.formInfoDTO;
import jhj.scanner.dto.scanDTO;
import jhj.scanner.dto.scanInfoDTO;
import jhj.scanner.dto.vulInfoDTO;

@Service
public class ScannerServiceImpl implements ScannerService {

	@Qualifier("dao")
	@Autowired
	ScannerDAO dao;

	@Override
	public void process_run(String url) {

		ScanningUrl scanBl = new ScanningUrl();
		System.out.println("���� ����????");
		scanDTO result = scanBl.scan(url);
		System.out.println("process_run end!!!");

		scanInfoDTO scan = result.getScaninfo();
		formInfoDTO form = result.getForminfo();
		List<vulInfoDTO> vulne = result.getVulinfo();

		System.out.println("[����][scaninfo] url: " + scan.getUrl() + "date: " + scan.getDate());
		System.out.println("[����][forminfo] form_name: " + form.getForm_name() + " tagid: " + form.getTagid());
		System.out.println("[����][vulinfo] " + vulne.size());

		dao.testSave3(scan, form, vulne);

	}

}
