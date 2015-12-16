package myspring.mvc.demo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AJAXHandler {

	// 處理AJAX Request
	@RequestMapping(value="/handleAJAXRequest")
	@ResponseBody
	public Map<String, String> handleAJAXRequest(@RequestParam String name, @RequestParam String age){
		String result = "false";
		Logger log = Logger.getLogger(getClass().getName());
		log.info("request");
		log.info(name);
		log.info(age);
		Map<String, String> map = new HashMap<String, String>();
		if(name != null && age != null){
			result = "true";
			map.put("name", name);
			map.put("age", age);
		}
		map.put("result", result);
		return map;
	}
}
