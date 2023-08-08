package practice.week15stream1

import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext._
import org.apache.log4j.{Level, Logger}

object StreamingWordCount extends App{
 
  //Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
  //Logger.getLogger("org.spark-project").setLevel(Level.ERROR)
  
  val sc = new SparkContext("local[*]","wordcount")
  
  Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
  
  //creating spark streaming context with two parameters - Spark Context and Batch Interval
  val ssc = new StreamingContext(sc,Seconds(15))
  
  //Lines is a Dstream holding many RDDs consuming from the socket 
  val lines = ssc.socketTextStream("localhost",9995)
  
  //words is a transformed Dstream
  val words = lines.flatMap(x => x.split(" "))
  
  val pairs = words.map(x => (x,1))
  val wordCounts = pairs.reduceByKey((x,y) => x+y)
  
  //calling an action
  wordCounts.print()
  
  //start the stream
  ssc.start()
  
  ssc.awaitTermination()
  
  
}