package jhj.scanner.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jhj.scanner.bo.ScanningProcesss;
import jhj.scanner.bo.scanningProcess;
import jhj.scanner.dao.ScannerDAO;

@Service
public class ScannerServiceImpl implements ScannerService {

	
	@Qualifier("dao")
	ScannerDAO dao;
	
	@Override
	public void process_run(String url) {
		// TODO Auto-generated method stub
		/*scanningProcess scanUrl = new scanningProcess();
		System.out.println("서비스 진입>>"+url);
		scanUrl.test(url);
		dao.process_run(url);*/
		
		ScanningProcesss scanUrl = new ScanningProcesss();
		scanUrl.scan(url);
	}

}
