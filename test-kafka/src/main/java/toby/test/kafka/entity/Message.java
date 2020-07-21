package toby.test.kafka.entity;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.springframework.messaging.MessageHeaders;

import lombok.Data;

@Data
public class Message implements org.springframework.messaging.Message<String> {

	private String message;
	
	private LocalDateTime sendTime;
	
	@Override
	public String getPayload() {
		
		return message;
	}

	@Override
	public MessageHeaders getHeaders() {
		
		return new MessageHeaders(new HashMap<>());
	}

}
