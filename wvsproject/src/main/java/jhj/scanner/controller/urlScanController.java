package jhj.scanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
		service.findResult(resultid);
		ModelAndView mav = new ModelAndView();
		//mav.addObject("ref", ref);
		mav.setViewName("member/login");
		return null;
	}

}
