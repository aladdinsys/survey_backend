package aladdinsys.aladdin_survey.global.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {

	@GetMapping("/survey")
	public String survey() {
		return "contents/survey";
	}


}
