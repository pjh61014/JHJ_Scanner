package jhj.scanner.dao;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.DBObject;

import jhj.scanner.dto.formInfoDTO;
import jhj.scanner.dto.scanInfoDTO;
import jhj.scanner.dto.vulInfoDTO;

@Repository("dao")
public class ScannerDAOImpl implements ScannerDAO {
	@Autowired 
	MongoTemplate mongoTemplate;
	
	public ScannerDAOImpl() {
		super();
		System.out.println("dao°´Ã¼»ý¼º++++++++++++++++++++++++++++++++++++");
	}

	

	@Override
	public void scanInsert(scanInfoDTO scanresult) {
		System.out.println("ddddd");
		mongoTemplate.insert(scanresult);
		
	
	}

	@Override
	public void formInsert(formInfoDTO formresult) {
		System.out.println("ddddd");
		mongoTemplate.insert(formresult);
	}

	@Override
	public void vulListInsert(List<vulInfoDTO> vulresult) {
		//mongoTemplate.insertAll(vulresult);
	}



	@Override
	public String toString() {
		System.out.println("daoÀÇ toString");
		return "ScannerDAOImpl [mongoTemplate=" + mongoTemplate + "]";
	}

	
}
