package jhj.scanner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ResultController {

	@RequestMapping(value="/result.do",method=RequestMethod.GET)
	public String ShowResult() {
		return "result";

	}

}
