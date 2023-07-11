package practice.week10

import org.apache.spark.SparkContext
import scala.io.Source

object BroadcastDemo extends App{
  
  def loadBoringWords():Set[String] = {
    
    var boringWords:Set[String] = Set()
    val lines = Source.fromFile("C:/All_WorkSpace/Data Engineering/Trendy Tech/Week10_Spark Indepth/boringwords-201014-183159.txt").getLines()
    
    for(line <- lines){
      boringWords += line

    }
    boringWords
  }
  
  
      val sc = new SparkContext("local[*]", "bigdatacampaign")
      
      //Using Broad cast variable
      var nameSet = sc.broadcast(loadBoringWords)
  
      val initital_rdd = sc.textFile("C:/All_WorkSpace/Data Engineering/Trendy Tech/Week10_Spark Indepth/bigdatacampaigndata-201014-183159.csv")
      
      val mappedInput = initital_rdd.map(x => (x.split(",")(10).toFloat, x.split(",")(0)))
      
      val words = mappedInput.flatMapValues(x => x.split(" "))
      
      val finalMapped = words.map(x => (x._2.toLowerCase(),x._1))
      
      //filter the data without shuffling
      val filteredRdd = finalMapped.filter(x => !nameSet.value(x._1))
      
      val total = filteredRdd.reduceByKey((x,y)=>x+y)
      
      val sorted = total.sortBy(x => x._2, false)
      
      sorted.take(20).foreach(println)
      
}