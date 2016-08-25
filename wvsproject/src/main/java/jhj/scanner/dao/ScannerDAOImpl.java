package jhj.scanner.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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
	public void scanResults(scanDTO results) {
		scanInfoDTO scan = results.getScaninfo();
		formInfoDTO form = results.getForminfo();
		List<vulInfoDTO> vulne = results.getVulinfo();

		System.out.println("[service][scaninfo] url: " + scan.getUrl() + "date: " + scan.getDate());
		System.out.println("[service][forminfo] form_name: " + form.getForm_name() + " tagid: " + form.getTagid());
		System.out.println("[service][vulinfo] " + vulne.size());

		scan.setForminfodto(form);
		scan.setVulinfo(vulne);
		mongoTemplate.save(scan);

	}

}
