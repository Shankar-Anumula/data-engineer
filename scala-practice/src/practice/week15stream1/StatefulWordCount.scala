package practice.week15stream1

import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext._

object StatefulWordCount extends App{
  
  val sc = new SparkContext("local[*]","wordcount")
    
  //creating spark streaming context with two parameters - Spark Context and Batch Interval
  val ssc = new StreamingContext(sc,Seconds(15))
  
  //Lines is a Dstream holding many RDDs consuming from the socket 
  val lines = ssc.socketTextStream("localhost",9995)
  
  def updateFunc(newValues:Seq[Int],previousState:Option[Int]):Option[Int] = {
    val newCount = previousState.getOrElse(0)+newValues.sum
    Some(newCount)
  }
  
  //check pointing to store the current state
  ssc.checkpoint(".")
  
  //words is a transformed Dstream 
  val words = lines.flatMap(x => x.split(" "))
  
  val pairs = words.map(x => (x,1))
  
  //reduceBy key is a stateless tranbsformation so we will change this
  //val wordCounts = pairs.reduceByKey((x,y) => x+y)
  
  val wordCounts = pairs.updateStateByKey(updateFunc)
    
  //calling an action
  wordCounts.print()
  
  //start the stream
  ssc.start()
  
  ssc.awaitTermination()
  
  
}