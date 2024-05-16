package week9

import org.apache.spark.SparkContext
import org.apache.log4j.Logger
import org.apache.log4j.Level

object WordCount3 extends App{
  
  /******************
  Modifications
	1. Sort by count of words Descending using SortBy	
  */
    Logger.getLogger("org").setLevel(Level.ERROR)
    val sc = new SparkContext("local[*]","WordCount")
    
    val results = sc.textFile("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week9_Apache Spark Introduction/search_data-201008-180523.txt")
                    .flatMap(x => x.split(" "))
                    .map(x => x.toLowerCase())
                    .map(x => (x,1))
                    .reduceByKey((x,y) => x+y)
                    .sortBy(x => x._2,false)
                    .collect
    
    for(result <- results){
      
      val word = result._1
      val count = result._2.toInt
      
      if(count > 10){
        println(s"$word: $count")
      }
      
    }
    

    scala.io.StdIn.readLine()
    
  //}
}