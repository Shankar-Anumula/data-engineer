package sparkindepth

import org.apache.spark.SparkContext

object groupDemo extends App{
  
  //Dataset: val baseRdd = sc.textFile("C:/All_WorkSpace/Data Engineering/Trendy Tech/Week10_Spark Indepth/bigLog.txt")

  val sc  = new SparkContext("local[*]","loglevel")
  
  sc.textFile("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week10_Spark Indepth/bigLog.txt")
                  .map(x => (x.split(":")(0),1)) 
                  .groupByKey.collect.foreach(x => println(x._1,x._2.size))
                  //.reduceByKey(_+_).collect.foreach(println)
                  
                  
  scala.io.StdIn.readLine()
}