package toby.querydsl.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import toby.querydsl.common.enums.BookCategory;

@RestController
@RequestMapping(path = "/test")
public class TestFormatController {
	
	@PostMapping(path = "/time-format")
	public LocalDateTime dateTimeStrFormat(@RequestParam LocalDateTime time, @RequestParam BookCategory category) {
		
		return time;
	}
}
