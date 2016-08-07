package jhj.scanner.dao;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.DBObject;

@Repository("dao")
public class ScannerDAOImpl implements ScannerDAO {
	@Autowired 
	MongoTemplate mongoTemplate;

	@Override
	public void process_run(JSONObject data) {
		DBObject dbObject = (DBObject)data;
		mongoTemplate.insert(dbObject);
		
	}
}
