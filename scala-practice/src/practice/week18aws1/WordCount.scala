package practice.week18aws1

import org.apache.spark.SparkContext
import org.apache.log4j.Level
import org.apache.log4j.Logger

object WordCount{
  
  def main(args:Array[String]){
    
    //setting to Log level to show ERRORS only
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    //Create a SparkContext using every core of the local machine
    //val sc = new SparkContext("local[*]","wordcount")
    val sc = new SparkContext()
    
    //Read each line of book into an RDD
    //val input = sc.textFile("C:/All_WorkSpace/Data Engineering/Trendy Tech/Week18_Big Data on Cloud Part-1/book-data.txt")
    val input = sc.textFile("s3n://shankar-aws1/book-data.txt")
    
    //processing 
    val words  =input.flatMap(x => x.split(" "))
    val wordCounts = words.map(x => (x,1))   
    val final_count = wordCounts.reduceByKey((a,b) => a+b)
    
    //Run Action to execute and collect the result
    val my_count  = final_count.collect
    my_count.foreach(println)
    
    scala.io.StdIn.readLine()
    
  }
    
}