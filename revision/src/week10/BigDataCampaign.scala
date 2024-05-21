package week10

import org.apache.spark.SparkContext


object BigDataCampaign extends App {
  
    val sc = new SparkContext("local[*]", "bigdatacampaign")
  
      val input = sc.textFile("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week10_Spark Indepth/bigdatacampaigndata-201014-183159.csv")
      
      val rdd1 = input.map(x => (x.split(",")(10).toFloat, x.split(",")(0)))
      
      val rdd2 = rdd1.flatMapValues(x => x.split(" "))
      
      val rdd3 = rdd2.map(x => (x._2.toLowerCase(),x._1))
      
      val rdd4 = rdd3.reduceByKey((x,y)=>x+y)
      
      val sorted = rdd4.sortBy(x => x._2, false)
      
      sorted.take(20).foreach(println)
      
      scala.io.StdIn.readLine()

      
}