package sparkbasics

import org.apache.spark.SparkContext

object WordCount extends App{
  
  //Dataset: C:\All_WorkSpace\Data-Engineering\Trendy Tech\Week9_Apache Spark Introduction\search_data-201008-180523.txt
  
    val sc = new SparkContext("local[*]","wordcount")
    
    val input = sc.textFile("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week9_Apache Spark Introduction/search_data-201008-180523.txt")
    
    val words = input.flatMap(x => x.split(" "))
    
    val wordMap = words.map(x =>(x,1))
    
    val finalCount = wordMap.reduceByKey((x,y) => (x+y))
    
    finalCount.collect().foreach(println)
    
    scala.io.StdIn.readLine()
  
}