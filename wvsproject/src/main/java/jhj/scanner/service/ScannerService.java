package jhj.scanner.service;

import jhj.scanner.dto.scanInfoDTO;

public interface ScannerService {

	String process_run(String url);
	scanInfoDTO findResult(String resultid);
	

}
