package toby.test.junit;

import static toby.test.config.constants.TopicConstant.TEST_TOPIC_NAME;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Producer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import toby.test.Application;

@DisplayName("测试topic和分区")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
class TopicPartitionTest {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private KafkaAdmin kafkaAdmin;

	@Autowired
	private ConsumerFactory<String, String> consumerFactory;

	@Test
	void obtainTopicInfo() throws Throwable {

		System.err.println(kafkaTemplate.partitionsFor(TEST_TOPIC_NAME));
		var topicPartitions = kafkaTemplate.partitionsFor(TEST_TOPIC_NAME).stream()
				.map(partitionInfo -> new TopicPartition(TEST_TOPIC_NAME, partitionInfo.partition()))
				.collect(Collectors.toSet());
		Optional.of(kafkaAdmin.getConfig()).ifPresent(
				map -> map.entrySet().forEach(entry -> System.err.println(entry.getKey() + ":" + entry.getValue())));

		Optional.of(consumerFactory.createConsumer().committed(topicPartitions)).ifPresent(
				map -> {
					System.err.println("输出commited offset");
					map.entrySet().forEach(entry -> System.err.println(entry.getKey() + ":" + entry.getValue()));
				});

		var lookUp = MethodHandles.lookup();
		var methodHandle = lookUp.bind(kafkaTemplate, "getTheProducer", MethodType.methodType(Producer.class));
		var producer = (Producer) methodHandle.invoke();
		Optional.of(producer.getProperties()).ifPresent(
				map -> map.entrySet().forEach(entry -> System.err.println(entry.getKey() + ":" + entry.getValue())));
	}
}
