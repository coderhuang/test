package toby.querydsl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping(path = "/index")
	public String index(Model model) {

		model.addAttribute("content", "1234567890");

		return "index";
	}
}
