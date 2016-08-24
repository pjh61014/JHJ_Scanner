package jhj.scanner.dao;

import java.util.List;

import jhj.scanner.dto.formInfoDTO;
import jhj.scanner.dto.scanInfoDTO;
import jhj.scanner.dto.vulInfoDTO;

public interface ScannerDAO {

	void testSave3(scanInfoDTO scanresult, formInfoDTO formresult,List<vulInfoDTO> vulne);
	
}
