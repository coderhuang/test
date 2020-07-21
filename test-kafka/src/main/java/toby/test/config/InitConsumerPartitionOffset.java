package toby.test.config;

import static toby.test.config.constants.TopicConstant.TEST_TOPIC_NAME;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

@Configuration
public class InitConsumerPartitionOffset implements CommandLineRunner {

	private final KafkaProperties properties;

	public InitConsumerPartitionOffset(KafkaProperties properties) {
		this.properties = properties;
	}

	@Override
	public void run(String... args) throws Exception {

		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties.buildConsumerProperties());
		consumer.subscribe(List.of(TEST_TOPIC_NAME));
		var assignment = consumer.assignment();
		while (CollectionUtils.isEmpty(assignment)) {
			consumer.poll(Duration.ofMillis(200));
			assignment = consumer.assignment();
		}
//		consumer.seekToBeginning(assignment);
//		consumer.seekToEnd(assignment);
		for (TopicPartition tp : assignment) {
			consumer.seek(tp, 100);
		}

		var tpBeginOffsets = consumer.beginningOffsets(assignment);
		var tpEndOffsets = consumer.endOffsets(assignment);
		Optional.of(tpBeginOffsets).ifPresent(map -> map.entrySet().forEach(entry -> System.err.println(entry.getKey() + ":" + entry.getValue())));
		Optional.of(tpEndOffsets).ifPresent(map -> map.entrySet().forEach(entry -> System.err.println(entry.getKey() + ":" + entry.getValue())));
		
		long nowInLastDay = LocalDateTime.now().minusDays(1L).toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
		consumer.offsetsForTimes(assignment.stream().collect(Collectors.toMap(k -> k, v -> nowInLastDay)));
	}

}
