package jhj.scanner.dao;

import jhj.scanner.dto.scanDTO;
import jhj.scanner.dto.scanInfoDTO;

public interface ScannerDAO {

	String scanResults(scanDTO results);
	scanInfoDTO findResults(String resultid);
	
}
