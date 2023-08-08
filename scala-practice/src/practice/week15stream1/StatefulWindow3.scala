package practice.week15stream1

import org.apache.spark.streaming.StreamingContext
import org.apache.spark.SparkContext
import org.apache.spark.streaming.Seconds

object StatefulWindow3 extends App{
  
  val sc = new SparkContext("local[*]","wordcount")
  
  val ssc = new StreamingContext(sc,Seconds(2))
  val lines = ssc.socketTextStream("localhost",9995)
  
  def summaryFunc(x:String,y:String)={
    (x.toInt+y.toInt).toString()
  }
  
  def inverseFunc(x:String,y:String)={
    (x.toInt-y.toInt).toString()
  }
  
  
  ssc.checkpoint(".")
  
  //reduceByWindow doesn't require pair RDD, works on normal RDD
  val word_counts = lines.reduceByWindow(summaryFunc,inverseFunc,Seconds(10),Seconds(2))
                          //.flatMap(x => x.split(" "))
                          //.map(x => (x,1))
                          //Summary function, Inverse Function, Window size, sliding interval
                          //.reduceByKeyAndWindow(summaryFunc(_,_),inverseFunc(_,_),Seconds(10),Seconds(2))
                          //.filter(x => x._2 > 0)
  
  word_counts.print()
  
  ssc.start()
  ssc.awaitTermination()
  
  
}