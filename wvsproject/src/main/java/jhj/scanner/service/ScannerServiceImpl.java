package jhj.scanner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jhj.scanner.bo.ScanningUrl;
import jhj.scanner.dao.ScannerDAO;
import jhj.scanner.dto.scanDTO;
import jhj.scanner.dto.scanInfoDTO;

@Service
public class ScannerServiceImpl implements ScannerService {

	@Qualifier("dao")
	@Autowired
	ScannerDAO dao;

	@Override
	public String process_run(String url) {

		ScanningUrl scanBl = new ScanningUrl();
		System.out.println("Business Object Entering...");
		scanDTO result = scanBl.scan(url);
		System.out.println("process_run end!!!");

		return dao.scanResults(result);

	}

	@Override
	public scanInfoDTO findResult(String scanresult) {
		System.out.println("findresults »£√‚ µ ");
		return dao.findResults(scanresult);
	}

}
