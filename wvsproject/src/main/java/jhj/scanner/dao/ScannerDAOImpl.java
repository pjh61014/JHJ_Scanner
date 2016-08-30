package jhj.scanner.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
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
		//scanInfoDTO mongotestVO =mongoTemplate.findOne(query, scanInfoDTO.class, "scanresult");
		System.out.println("[ObjectId]" + mongotestVO.get_id()
		+"[URL]"+mongotestVO.getUrl()+"[DATE]"+mongotestVO.getDate()+
		"vultype"+mongotestVO.getVul_type());
		
		formInfoDTO forminfo = mongotestVO.getForminfo();
		List<vulInfoDTO> vulinfo = mongotestVO.getVulinfo();
		System.out.println(forminfo.getForm_name().length+forminfo.getForm_name().toString());
		String [] test = forminfo.getForm_name();
		System.out.println(test.length);
		
		String [] test2 = forminfo.getTagid();
		
		
		for (int i = 0; i < test.length; i++) {
			System.out.println(test[i]);
		}
		
		for (int i = 0; i < test2.length; i++) {
			System.out.println(test2[i]);
		}
		
		int size = vulinfo.size();
		
		for (int i = 0; i < size; i++) {
			vulInfoDTO ss = vulinfo.get(i);
			System.out.print("vulname"+ss.getVul_name()+"\t");
			System.out.print("pattern"+ss.getPattern()+"\t");
			System.out.print("pattern_id"+ss.getPatterm_id()+"\t");
			System.out.print("pattern_desp"+ss.getPattern_dspt()+"\t");
			System.out.println();
		}
		/*Query query2 = new Query();
		//formInfoDTO form= Query.query(Criteria.where("forminfo").exists(true));
		//-------------------
		Criteria criteria= new Criteria();
		criteria.andOperator(Criteria.where("resultid").is(resultid),
				Criteria.where("forminfo").exists(true));
		Query query = new Query(criteria);
		formInfoDTO dto= mongoTemplate.findOne(query, formInfoDTO.class,"scanresult");
		
		System.out.println("dto findresult"+dto.getForm_name().length);*/
		
		//----------------
		/*
		Query query = new Query(Criteria.where("resultid").is(resultid));
		query.fields().include("forminfo.form_name");
		query.fields().include("forminfo.tagid");
		System.out.println(query.toString());
		formInfoDTO test = mongoTemplate.findOne(query, formInfoDTO.class,"scanresult");
		System.out.println(test.toString());*/
		
		/*int size = dto.size();
		
		for (int i = 0; i < size; i++) {
			formInfoDTO dt= dto.get(i);
			System.out.println(dt.getForm_name().toString());
			System.out.println(dt.getTagid().toString());
			
		}*/
		
		//-------------------------------
		/*Query query = new Query(new Criteria().andOperator(
				Criteria.where("resultid").is(resultid),Criteria.where(
						"forminfo").exists(true)));
		System.out.println(query);
		List<formInfoDTO> test = mongoTemplate.find(query, formInfoDTO.class,"scanresult");
		
		for(formInfoDTO dd:test){
			System.out.println(dd.getForm_name());
			System.out.println(dd.getTagid());
		}*/
		
		
	}

}
