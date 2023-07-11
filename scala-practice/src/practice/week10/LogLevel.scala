package practice.week10

import org.apache.spark.SparkContext

object LogLevel extends App{
  val sc  = new SparkContext("local[*]","loglevel")
  
  val myList = List("WARN: Tuesday 4 September 0405",
                    "ERROR: Tuesday 4 September 0408",
                    "ERROR: Tuesday 4 September 0408",
                    "ERROR: Tuesday 4 September 0408",
                    "ERROR: Tuesday 4 September 0408",
                    "ERROR: Tuesday 4 September 0408")
  
  //create RDD from local collection  - List in this case
  val originalLogsRdd = sc.parallelize(myList)
  
  val newPairRdd = originalLogsRdd.map(x => {
                                              val columns = x.split(":")
                                              val logLevel = columns(0)
                                              (logLevel,1)
                                            }
                                       )
  
  val resultantRdd = newPairRdd.reduceByKey((x,y) => x +y)
  resultantRdd.collect().foreach(println)
  
  
  //chaining up functions
  
  sc.parallelize(myList)
    .map(x => (x.split(":")(0),1))
    .reduceByKey((x,y) => x +y)
    .collect().foreach(println)
 
 // scala.io.StdIn.readLine()


} 