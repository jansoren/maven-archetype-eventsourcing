package ${package}.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/example")
public class ExampleController {

	private static Logger log = LoggerFactory.getLogger(ExampleController.class);
	
	@RequestMapping(value = "get", method = RequestMethod.GET)
	@ResponseBody
	public Example getExample() {
		log.info("Getting example");
		return new Example();
	}
}
