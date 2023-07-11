package practice.week10

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD.rddToPairRDDFunctions
import org.apache.log4j.Level
import org.apache.log4j.Logger


object BigDataCampaign extends App {
  
  Logger.getLogger("org").setLevel(Level.ERROR)
  
    val sc = new SparkContext("local[*]", "bigdatacampaign")
  
      val initital_rdd = sc.textFile("C:/All_WorkSpace/Data Engineering/Trendy Tech/Week10_Spark Indepth/bigdatacampaigndata-201014-183159.csv")
      
      val mappedInput = initital_rdd.map(x => (x.split(",")(10).toFloat, x.split(",")(0)))
       
      val words = mappedInput.flatMapValues(x => x.split(" "))
      
      val finalMapped = words.map(x => (x._2.toLowerCase(),x._1))
      
      val totalByCustomer = finalMapped.reduceByKey((x,y)=>x+y)
      
      val sortedByTotal = totalByCustomer.sortBy(x => x._2, false)
      
      //sorted.saveAsTextFile("C:/All_WorkSpace/Data Engineering/Trendy Tech/Week10_Spark Indepth/spark_output")
      
      val premiumCustomers = sortedByTotal.filter(x => x._2 > 5000)
      
      //val doubledPurchase = premiumCustomers.map(x => (x._1,x._2 *2))
      
      //doubledPurchase.collect.foreach(println)
      
      //println(doubledPurchase.count)
      
      //scala.io.StdIn.readLine()
  
      
      //sorted.take(20).foreach(println)
      
      premiumCustomers.take(10).foreach(println)
      
}