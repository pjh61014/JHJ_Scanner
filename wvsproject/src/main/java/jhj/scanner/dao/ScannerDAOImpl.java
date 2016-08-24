package jhj.scanner.dao;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.DBObject;

import jhj.scanner.dto.Address;
import jhj.scanner.dto.formInfoDTO;
import jhj.scanner.dto.scanInfoDTO;
import jhj.scanner.dto.vulInfoDTO;

@Repository("dao")
public class ScannerDAOImpl implements ScannerDAO {
	@Autowired 
	MongoTemplate mongoTemplate;
	
	
	@Override
	public void testSave3(scanInfoDTO scanresult, formInfoDTO formresult, List<vulInfoDTO> vulne) {
		scanresult.setForminfodto(formresult);
		scanresult.setVulinfo(vulne);
		mongoTemplate.save(scanresult);
		
	}
	
	

	
}
