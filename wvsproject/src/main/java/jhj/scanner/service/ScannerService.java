package jhj.scanner.service;

import java.util.List;

import jhj.scanner.dto.formInfoDTO;
import jhj.scanner.dto.scanDTO;
import jhj.scanner.dto.scanInfoDTO;
import jhj.scanner.dto.vulInfoDTO;

public interface ScannerService {

	void process_run(String url);
	void resultInsert(scanInfoDTO scaninfo,List<vulInfoDTO> vulinfolist, formInfoDTO forminfo);

}
