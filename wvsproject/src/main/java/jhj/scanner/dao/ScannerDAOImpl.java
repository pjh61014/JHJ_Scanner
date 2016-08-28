package jhj.scanner.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import jhj.scanner.dto.formInfoDTO;
import jhj.scanner.dto.scanDTO;
import jhj.scanner.dto.scanInfoDTO;
import jhj.scanner.dto.vulInfoDTO;

@Repository("dao")
public class ScannerDAOImpl implements ScannerDAO {
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public String scanResults(scanDTO results) {
		scanInfoDTO scan = results.getScaninfo();
		formInfoDTO form = results.getForminfo();
		List<vulInfoDTO> vulne = results.getVulinfo();

		System.out.println("[DAO][scaninfo] url: " + scan.getUrl() + "date: " + scan.getDate());
		System.out.println("[DAO][forminfo] form_name: " + form.getForm_name() + " tagid: " + form.getTagid());
		System.out.println("[DAO][vulinfo] " + vulne.size());

		scan.setForminfodto(form);
		scan.setVulinfo(vulne);
		mongoTemplate.save(scan);
		System.out.println("[DAO]삽입완료 resultsID: "+scan.getResultid());
		return scan.getResultid();

	}

	@Override
	public void findResults(String resultid) {
		// sqlinjection2
		System.out.println("[DAO]findResult 진입 ");
		String key ="resultid";
		Criteria criteria = new Criteria(key);
		criteria.is(resultid);
		Query query = new Query(criteria);
		
		scanInfoDTO mongotestVO =mongoTemplate.findOne(query, scanInfoDTO.class,"scanresult");
		System.out.println("[ObjectId]" + mongotestVO.get_id()
		+"[URL]"+mongotestVO.getUrl()+"[DATE]"+mongotestVO.getDate());
		
	}

}
