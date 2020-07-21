package toby.test.kafka.producer;

import java.util.Map;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class SecondInterceptor implements ProducerInterceptor<String, String> {

	@Override
	public void configure(Map<String, ?> configs) {
	}

	@Override
	public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
		
		System.err.println("SecondInterceptor 测试发送信息前");
		return record;
	}

	@Override
	public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
		
		
	}

	@Override
	public void close() {
	}

}
