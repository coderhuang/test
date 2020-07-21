package toby.test.kafka;

import static toby.test.config.constants.TopicConstant.TEST_TOPIC_NAME;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Sender {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void send(String message) throws InterruptedException, ExecutionException {

		var listenableFuture = kafkaTemplate.send(TEST_TOPIC_NAME, message);
		log.info("sended message:{}", listenableFuture.get().getProducerRecord().value());
	}
}
