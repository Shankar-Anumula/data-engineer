package practice.week10

import org.apache.spark.SparkContext

object groupByKeyDemo extends App {
  val sc  = new SparkContext("local[*]","loglevel")
  
  val baseRdd = sc.textFile("C:/All_WorkSpace/Data Engineering/Trendy Tech/Week10_Spark Indepth/bigLog.txt")
                    
  val mappedRdd = baseRdd.map(x => {
                                      val fields = x.split(":")
                                      //(fields(0),fields(1))
                                      (fields(0),1)
                                   }
                              ) 
  
                           
  //mappedRdd.groupByKey.collect().foreach(x => println(x._1,x._2.size))
  
  mappedRdd.reduceByKey(_+_).collect.foreach(println)
  
  scala.io.StdIn.readLine()
}