package toby.test.kafka;

import static toby.test.config.constants.TopicConstant.TEST_TOPIC_NAME;

import java.util.Optional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Receiver {

	@KafkaListener(topics = TEST_TOPIC_NAME)
	public void listen(ConsumerRecord<String, String> record) {

		var optionalRecord = Optional.ofNullable(record);
		optionalRecord.ifPresent(r -> log.error("收到消息:{}", r.value()));
		System.err.println("最后的消费偏移量:" + record.offset());
	}
}
