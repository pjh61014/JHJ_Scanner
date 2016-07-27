package jhj.scanner.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jhj.scanner.bo.ScanningProcess;
import jhj.scanner.dao.ScannerDAO;

@Service
public class ScannerServiceImpl implements ScannerService {

	
	@Qualifier("dao")
	ScannerDAO dao;
	
	@Override
	public void process_run(String url) {
		// TODO Auto-generated method stub
		ScanningProcess scanUrl = new ScanningProcess();
		System.out.println("서비스 진입>>"+url);
		scanUrl.Scan(url);
		//dao.process_run(url);
	}

}
