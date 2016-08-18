package jhj.scanner.service;

import java.util.List;

import jhj.scanner.dto.scanInfoDTO;

public interface ScannerService {

	void process_run(String url);
	public void insertDocument(scanInfoDTO doc);
	public void insertAllDocument(List<scanInfoDTO> docs);
}
