package jhj.scanner.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import jhj.scanner.dto.scanInfoDTO;
import jhj.scanner.dto.testDTO;

@Repository("dao")
public class ScannerDAOImpl implements ScannerDAO {

	
	@Autowired
	MongoTemplate mongoTemplate;
	
	
	@Override
	public void process_run(String url) {
	}

	@Override
	public void insertDocument(scanInfoDTO doc) {
		mongoTemplate.insert(doc);
	}

	@Override
	public void insertAllDocument(List<scanInfoDTO> docs) {
		mongoTemplate.insertAll(docs);
	}
}
