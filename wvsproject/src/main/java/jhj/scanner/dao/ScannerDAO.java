package jhj.scanner.dao;

import java.util.List;

import jhj.scanner.dto.formInfoDTO;
import jhj.scanner.dto.scanInfoDTO;
import jhj.scanner.dto.vulInfoDTO;

public interface ScannerDAO {

	void scanInsert(scanInfoDTO scanresult);
	void formInsert(formInfoDTO formresult);
	void vulListInsert(List<vulInfoDTO> vulresult);
	
}
