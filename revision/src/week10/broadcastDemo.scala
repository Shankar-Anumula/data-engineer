package week10

import org.apache.spark.SparkContext
import scala.io.Source

object broadcastDemo extends App{
  
  //This code is running on driver machine and the result is broadcasted to executors
  def loadBoringWords:Set[String] = {
    
    var boringWords:Set[String]=Set()
    val lines = Source.fromFile("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week10_Spark Indepth/boringwords-201014-183159.txt").getLines()

    for(line <- lines){
      boringWords += line
    }
    
    boringWords
  }
  
  
      val sc = new SparkContext("local[*]", "bigdatacampaign")
  
      var nameSet = sc.broadcast(loadBoringWords)
      
      val initial_rdd = sc.textFile("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week10_Spark Indepth/bigdatacampaigndata-201014-183159.csv")

      val mappedInput = initial_rdd.map(x => (x.split(",")(10).toFloat, x.split(",")(0)))
      
      val words = mappedInput.flatMapValues(x => x.split(" "))
      
      val finalMappedWords = words.map(x => (x._2.toLowerCase(),x._1))
      
      //This method will return boolean, if the boringword is found a true is returned. So all non-boring words are considered with "!"d
      //(big, 24)
      //(is, 25)
      val filteredRdd = finalMappedWords.filter(x => !nameSet.value(x._1))
      
      val total = filteredRdd.reduceByKey((x,y)=>x+y)
      
      val sorted = total.sortBy(x => x._2, false)
      
      sorted.take(20).foreach(println)
      
    scala.io.StdIn.readLine()
  
}