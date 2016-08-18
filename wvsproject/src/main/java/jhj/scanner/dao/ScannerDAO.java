package jhj.scanner.dao;

import java.util.List;

import jhj.scanner.dto.scanInfoDTO;
import jhj.scanner.dto.testDTO;

public interface ScannerDAO {

	void process_run(String url);
	public void insertDocument(scanInfoDTO doc);
	public void insertAllDocument(List<scanInfoDTO> docs);
}
