package jhj.scanner.dao;

import jhj.scanner.dto.scanDTO;

public interface ScannerDAO {

	String scanResults(scanDTO results);
	void findResults(String resultid);
	
}
