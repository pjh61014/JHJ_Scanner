package jhj.scanner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jhj.scanner.bo.ScanningUrl;
//github.com/pjh61014/JHJ_Scanner.git
import jhj.scanner.dao.ScannerDAO;
import jhj.scanner.dto.formInfoDTO;
import jhj.scanner.dto.scanDTO;
import jhj.scanner.dto.scanInfoDTO;
import jhj.scanner.dto.vulInfoDTO;

@Service
public class ScannerServiceImpl implements ScannerService {
	
	@Qualifier("dao")
	@Autowired
	ScannerDAO dao;
	
	@Override
	public void process_run(String url) {
		
		
		ScanningUrl scanBl = new ScanningUrl();
		System.out.println("서비스 들어옴????");
		scanDTO result = scanBl.scan(url);
		System.out.println("process_run end!!!");
		
		scanInfoDTO scan = result.getScaninfo();
		formInfoDTO form = result.getForminfo();
		List<vulInfoDTO> vulne = result.getVulinfo();
		
		
		System.out.println("[서비스][scaninfo] url: "+scan.getUrl()+"date: "+scan.getDate());
		System.out.println("[서비스][forminfo] form_name: "+form.getForm_name()+" tagid: "+form.getTagid()+" code: "+form.get_id());
		System.out.println("[서비스][vulinfo] "+vulne.size());
		
		//dao.scanInsert(scan);
		dao.formInsert(form);
		//System.out.println("서비스"+data.toJSONString());
		//dao.process_run(data);
	
		/*EntryPointUrl scanB2 = new EntryPointUrl();
		System.out.println("서비스 들어옴");
		scanB2.scan(url);*/
	}

	@Override
	public void resultInsert(scanInfoDTO scaninfo, List<vulInfoDTO> vulinfolist, formInfoDTO forminfo) {
		System.out.println("서비스 resultInsert 들어옴...");
		scanInfoDTO scan = scaninfo;
		formInfoDTO form = forminfo;
		List<vulInfoDTO> vulne= vulinfolist;
		
		
		System.out.println("[서비스][scaninfo] url: "+scan.getUrl()+"date: "+scan.getDate());
		System.out.println("[서비스][forminfo] form_name: "+form.getForm_name()+" tagid: "+form.getTagid());
		System.out.println("[서비스][vulinfo] "+vulne.size());
		
		int size = vulne.size();
		for (int i = 0; i < size; i++) {
			vulInfoDTO vuldto = vulne.get(i);
			// System.out.println(vuldto.getPattern_dspt());
			System.out.println("vulname: " + vuldto.getVul_name() + "pattern_id: " + vuldto.getPatterm_id()
					+ "pattern: " + vuldto.getPattern() + "pattern_id" + vuldto.getPattern_dspt() + " vultag: "
					+ vuldto.get_id());
		}
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&"+scan.toString());
		
		dao.scanInsert(scan);
		//dao.formInsert(form);
		//dao.vulListInsert(vulne);
		
	}

	

	

}
