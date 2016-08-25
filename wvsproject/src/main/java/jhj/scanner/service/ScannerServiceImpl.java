package jhj.scanner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jhj.scanner.bo.ScanningUrl;
//github.com/pjh61014/JHJ_Scanner.git
import jhj.scanner.dao.ScannerDAO;
import jhj.scanner.dto.scanDTO;

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

		dao.scanResults(result);

	}

}
