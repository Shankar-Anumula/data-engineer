package practice.week17kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;


public class MyProducer {
	
	public static void main(String[] args) {
		
		//Step 1 - setting the properties
		//Client-Id, bootstrap-server-list,key serializer class, value serializer class
		
		//1 , "THIS IS MY FIRST MESSAGE"
		
		Properties props = new Properties();
		props.put(ProducerConfig.CLIENT_ID_CONFIG, "producer-id1");
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,IntegerSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
		
		
		//Step 2 - Create Object of Kafka Producer
		
		KafkaProducer<Integer, String> producer = new KafkaProducer<Integer, String>(props);
		
		//Step 3 - calling the send methos on this producer object
		
		producer.send(new ProducerRecord<Integer,String>("ournewtopic",1,"THIS IS MY FIRST MESSAGE"));
		
		
		//Step 4 - close the producer object
		
		producer.close();
		
	}

}
