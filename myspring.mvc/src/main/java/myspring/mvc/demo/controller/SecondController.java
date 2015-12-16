package myspring.mvc.demo.controller;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/mySecond")
public class SecondController {
	
	@RequestMapping(value="/second", method=RequestMethod.GET)
	public String second(ModelMap model){
		Logger log  = Logger.getLogger(getClass().getName());
		log.info("second");
		model.addAttribute("message", "my Second controller");
		return "second";
	}
}
