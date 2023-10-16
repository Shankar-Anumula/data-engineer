package practice.week17kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Properties;

public class MyConsumer {

	public static void main(String[] args) {
		
		//Set a bunch of properties
		Properties consumerProps = new Properties();
		consumerProps.put(ConsumerConfig.CLIENT_ID_CONFIG, ConstantConfig.appID);
		consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, ConstantConfig.bootstrapServerList);
		consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,IntegerDeserializer.class.getName());
		consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
		
		consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG,"CONSUMER_GROUP1");
		consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest"); 
		//default value is latest - After the cosnumer subscribes to the group it can read the messages produced after that point
		//earliest - if we want to read the messages produced before the consumer subscribes
		
		//Step 2 - Create Object of Kafka Consumer and subscribe to topics
		KafkaConsumer<Integer, String> consumer = new KafkaConsumer<Integer, String>(consumerProps);
		consumer.subscribe(Arrays.asList("all_orders"));
		
		//Step 3 - Set Producer properties
		Properties producerProps = new Properties();
		producerProps.put(ProducerConfig.CLIENT_ID_CONFIG, ConstantConfig.appID);
		producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, ConstantConfig.bootstrapServerList);
		producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,IntegerSerializer.class.getName());
		producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
		
		//Step 4 - Create Object of Kafka Producer
		KafkaProducer<Integer, String> producer = new KafkaProducer<Integer, String>(producerProps);
		
		while(true){
			ConsumerRecords<Integer, String> records = consumer.poll(100);
			
			for(ConsumerRecord<Integer,String> record: records) {
				if(record.value().split(",")[3].equals("CLOSED"))
					producer.send(new ProducerRecord<Integer, String>("closed_orders",record.key(),record.value()));
				else
					producer.send(new ProducerRecord<Integer, String>("completed_orders",record.key(),record.value()));
			}
		}
		
		//close the consumer
		//consumer.close();
		//producer.close();
		
	}
}
