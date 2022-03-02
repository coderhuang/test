package web.vo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class HelloWorldVO {

	private LocalDateTime time;

	private String message;
}
