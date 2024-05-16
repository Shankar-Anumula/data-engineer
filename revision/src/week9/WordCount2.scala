package week9

import org.apache.spark.SparkContext
import org.apache.log4j.Logger
import org.apache.log4j.Level

object WordCount2 extends App{
  
  /******************
  Modifications
  /
	1. Count ignoring the case (used lowercase)
	2. Sort by count of words Descending using SortByKey
	3. Filter words greater than 10
	
  */
    Logger.getLogger("org").setLevel(Level.ERROR)
    val sc = new SparkContext("local[*]","WordCount")
    
    val inputRDD = sc.textFile("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week9_Apache Spark Introduction/search_data-201008-180523.txt")
    val words = inputRDD.flatMap(x => x.split(" "))
    
    //1. Count ignoring the case (used lowercase)
    val wordsLowerCase = words.map(x => x.toLowerCase())
    
    val wordMap = words.map(x => (x,1))
    val tupleCount = wordMap.reduceByKey((x,y) => x+y)
    
    //reverse the key value so that sprtbykey can be applied
    val reversedTupleFinalCount = tupleCount.map(x => (x._2,x._1))
    
    //By Default sort by key is ascending so give false to descending order sort
    val descSortedCount = reversedTupleFinalCount.sortByKey(false)
    
    val finalCount = descSortedCount.map(x => (x._2,x._1))
    
    //Run action to execute and collect the result
    val results = finalCount.collect
    
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