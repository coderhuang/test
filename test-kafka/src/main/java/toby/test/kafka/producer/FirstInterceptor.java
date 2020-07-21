package toby.test.kafka.producer;

import java.util.Map;
import java.util.Objects;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FirstInterceptor implements ProducerInterceptor<String, String> {

	@Override
	public void configure(Map<String, ?> configs) {
	}

	@Override
	public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
		
		System.err.println("测试发送信息前");
		return record;
	}

	@Override
	public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
		
		if (Objects.nonNull(metadata)) {
			
			System.err.println("消息发送成功");
		} else {
			
			System.err.println("sent is encounter an error");
			log.error("{}", exception.getMessage());
		}
	}

	@Override
	public void close() {
	}

}
