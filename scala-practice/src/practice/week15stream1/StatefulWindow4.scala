package practice.week15stream1

import org.apache.spark.streaming.StreamingContext
import org.apache.spark.SparkContext
import org.apache.spark.streaming.Seconds

object StatefulWindow4 extends App{
  
  val sc = new SparkContext("local[*]","wordcount")
  
  val ssc = new StreamingContext(sc,Seconds(2))
  val lines = ssc.socketTextStream("localhost",9995)
  
  ssc.checkpoint(".")
  
  //parameters - window size, sliding interval
  val word_counts = lines.countByWindow(Seconds(10),Seconds(2))
  
  word_counts.print()
  
  ssc.start()
  ssc.awaitTermination()
  
}