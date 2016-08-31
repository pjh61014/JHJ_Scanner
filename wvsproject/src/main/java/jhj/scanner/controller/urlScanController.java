package jhj.scanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jhj.scanner.dto.scanInfoDTO;
import jhj.scanner.service.ScannerService;

@Controller
public class urlScanController {

	@Autowired
	ScannerService service;

	@RequestMapping(value = "/urlscan.do", method = RequestMethod.POST)
	public ModelAndView loginForm(String url){
		System.out.println("컨트롤러 들어옴!!"+url);
		String resultid =service.process_run(url);
		System.out.println("resultid 리턴값 확인: "+resultid);
		scanInfoDTO results= service.findResult(resultid);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("targetResult", results);
		mav.setViewName("targetScanResult");
		return mav;
		
	/*	
		formInfoDTO forminfo = mongotestVO.getForminfo();
		List<vulInfoDTO> vulinfo = mongotestVO.getVulinfo();
		
		String [] test = forminfo.getForm_name();
		
		
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
		}*/
		
	}

}
