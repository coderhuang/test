package toby.test.junit;

import static toby.test.config.constants.TopicConstant.TEST_TOPIC_NAME;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;
import toby.test.Application;
import toby.test.kafka.Sender;

@DisplayName("啊哈")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {
		Application.class
})
@Slf4j
@AutoConfigureMockMvc
class SenderTest {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	private Sender sender;
	
	@Autowired
	private ConsumerFactory<String, String> consumerFactory;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		log.warn("-----------@BeforeAll--------------");
	}

	@BeforeEach
	void setUp() throws Exception {
		log.warn("-----------@BeforeEach--------------");
	}

	@Test
	@DisplayName("kafka发送")
	void test() throws InterruptedException, ExecutionException {
		
		sender.send("hello world");
		log.info("kafka send is done");
		
		var topicPartitions = kafkaTemplate.partitionsFor(TEST_TOPIC_NAME).stream()
				.map(partitionInfo -> new TopicPartition(TEST_TOPIC_NAME, partitionInfo.partition()))
				.collect(Collectors.toSet());
		var consumer = consumerFactory.createConsumer();
		consumer.commitSync();
		Optional.of(consumer.committed(topicPartitions)).ifPresent(
				map -> {
					System.err.println("输出commited offset");
					map.entrySet().forEach(entry -> System.err.println(entry.getKey() + ":" + entry.getValue()));
				});
	}

	@AfterEach
	void tearDown() throws Exception {
		log.warn("-----------@AfterEach--------------");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		log.warn("-----------@AfterAll--------------");
	}

}
