package jhj.scanner.service;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mongodb.util.JSON;

import jhj.scanner.bo.EntryPointUrl;
import jhj.scanner.bo.ScanningUrl;
//github.com/pjh61014/JHJ_Scanner.git
import jhj.scanner.dao.ScannerDAO;

@Service
public class ScannerServiceImpl implements ScannerService {

	
	@Qualifier("dao")
	ScannerDAO dao;
	
	@Override
	public void process_run(String url) {
		// TODO Auto-generated method stub
		/*scanningProcess scanUrl = new scanningProcess();
		System.out.println("���� ����>>"+url);
		scanUrl.test(url);
		dao.process_run(url);*/
		
		ScanningUrl scanBl = new ScanningUrl();
		System.out.println("���� ����????");
		JSONObject data =scanBl.scan(url);
		System.out.println("����"+data.toJSONString());
		//dao.process_run(data);
	
		/*EntryPointUrl scanB2 = new EntryPointUrl();
		System.out.println("���� ����");
		scanB2.scan(url);*/
	}

}
