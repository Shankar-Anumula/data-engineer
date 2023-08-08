package practice.week15stream1

import org.apache.spark.streaming.StreamingContext
import org.apache.spark.SparkContext
import org.apache.spark.streaming.Seconds

object StatefulWindow extends App{
  
  val sc = new SparkContext("local[*]","wordcount")
  
  val ssc = new StreamingContext(sc,Seconds(2))
  val lines = ssc.socketTextStream("localhost",9995)
  
  /*
   * def updateFunc(newValues:Seq[Int],previousState:Option[Int]):Option[Int] = {
    val newCount = previousState.getOrElse(0)+newValues.sum
    Some(newCount)
  }
  */
  
  ssc.checkpoint(".")
  
  val word_counts = lines.flatMap(x => x.split(" "))
                          .map(x => (x,1))
                          //.updateStateByKey(updateFunc)
                          //Summary function, Inverse Function, Window size, sliding interval
                          .reduceByKeyAndWindow((x,y) => x+y,(x,y) => x-y,Seconds(10),Seconds(2))
                          .filter(x => x._2 > 0)
                          //Filter to not display words with count 0
  
  word_counts.print()
  
  ssc.start()
  ssc.awaitTermination()
  
  
}