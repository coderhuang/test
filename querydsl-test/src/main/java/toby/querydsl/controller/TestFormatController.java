package toby.querydsl.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/test")
public class TestFormatController {
	
	@PostMapping(path = "/time-format")
	public LocalDateTime dateTimeStrFormat(LocalDateTime time) {
		
		return time;
	}
}
