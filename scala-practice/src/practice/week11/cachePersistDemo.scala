package practice.week11

import org.apache.spark.SparkContext
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.storage.StorageLevel

object cachePersistDemo extends App{
  Logger.getLogger("org").setLevel(Level.ERROR)
  
    val sc = new SparkContext("local[*]", "bigdatacampaign")
  
      val initital_rdd = sc.textFile("C:/All_WorkSpace/Data Engineering/Trendy Tech/Week11_Spark Structured API - Part1/customerorders-201008-180523.csv")
      
      val mappedInput = initital_rdd.map(x => (x.split(",")(0), x.split(",")(2).toFloat))
             
      val totalByCustomer = mappedInput.reduceByKey((x,y)=>x+y)
                  
      val premiumCustomers = totalByCustomer.filter(x => x._2 > 5000)
      
      val doubledPurchase = premiumCustomers.map(x => (x._1,x._2 *2)).persist(StorageLevel.MEMORY_AND_DISK)
      
      doubledPurchase.collect.foreach(println)
      
      println(doubledPurchase.count)
      
      scala.io.StdIn.readLine()
  
}