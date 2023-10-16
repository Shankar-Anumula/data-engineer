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
		//props.put(ProducerConfig.CLIENT_ID_CONFIG, "producer-id1");
		//props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094");
		
		props.put(ProducerConfig.CLIENT_ID_CONFIG, ConstantConfig.appID);
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, ConstantConfig.bootstrapServerList);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,IntegerSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
		
		
		//Step 2 - Create Object of Kafka Producer
		KafkaProducer<Integer, String> producer = new KafkaProducer<Integer, String>(props);
		
		//Step 3 - calling the send method on this producer object
		/*for(int i=1;i<50;i++) {
			producer.send(new ProducerRecord<Integer,String>(ConstantConfig.topicName,i,"THIS IS MY MESAGE NUMBER" +i));
		}*/
		
		producer.send(new ProducerRecord<Integer,String>(ConstantConfig.topicName,1,"1, 2013-07-25, 00:00:00.0,256,CLOSED"));
		producer.send(new ProducerRecord<Integer,String>(ConstantConfig.topicName,2,"2, 2013-07-25, 00:00:00.0,157,COMPLETE"));
		producer.send(new ProducerRecord<Integer,String>(ConstantConfig.topicName,3,"3, 2013-07-25, 00:00:00.0,257,CLOSED"));
		producer.send(new ProducerRecord<Integer,String>(ConstantConfig.topicName,4,"4, 2013-07-25, 00:00:00.0,158,COMPLETE"));
		
		//Step 4 - close the producer object
		producer.close();
		
	}

}
