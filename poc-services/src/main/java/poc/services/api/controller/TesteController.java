package poc.services.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteController {
	@Value("${server.port}")
	String porta;

	@RequestMapping(value = "/")
	public String home() {
		return "Mrs Ribbon, estou vivo!";
	}

	@RequestMapping("/greeting")
	public String hello() {
		return "Porta: " + porta + "!";
	}
}
