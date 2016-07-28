package myspring.mvc.demo.controller;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/myMain")
public class MyMainController {

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String show(ModelMap model){
		Logger log  = Logger.getLogger(getClass().getName());
		log.info("show");
		model.addAttribute("message", "Hi, test Spring MVC");
		return "hello";
	}
}
