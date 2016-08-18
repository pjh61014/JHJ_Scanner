package jhj.scanner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jhj.scanner.bo.ScanningUrl;
//github.com/pjh61014/JHJ_Scanner.git
import jhj.scanner.dao.ScannerDAO;
import jhj.scanner.dto.scanInfoDTO;
import jhj.scanner.dto.testDTO;

@Service
public class ScannerServiceImpl implements ScannerService {


	@Qualifier("dao")
	@Autowired
	ScannerDAO dao;
	
	@Override
	public void process_run(String url) {
		// TODO Auto-generated method stub
		/*scanningProcess scanUrl = new scanningProcess();
		System.out.println("서비스 진입>>"+url);
		scanUrl.test(url);
		dao.process_run(url);*/
		
		ScanningUrl scanBl = new ScanningUrl();
		System.out.println("서비스 들어옴????"+url);
		scanInfoDTO scandto = scanBl.scan(url);
		scanInfoDTO aa = new scanInfoDTO();
		aa.setUrl("testest.com");
		testDTO testing = new testDTO();
		testing.setUrl(url);
		testing.setDate("2016-08-19");
		dao.insertDocument(scandto);
		
		
		
		System.out.println("hhh");
	}

	@Override
	public void insertDocument(scanInfoDTO doc) {
		dao.insertDocument(doc);
		
	}

	@Override
	public void insertAllDocument(List<scanInfoDTO> docs) {
		dao.insertAllDocument(docs);
	}
}
