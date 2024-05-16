package week9

import org.apache.spark.SparkContext
import org.apache.log4j.Logger
import org.apache.log4j.Level

object WordCount extends App{
  
  //def main(args:Array[String]){
  
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    //In IDE, spark context is not available so we need to create it. 
    //Parameters - Use Local CPU cores * means use all cores, and show the application as wordcount
    val sc = new SparkContext("local[*]","WordCount")
    
    //Load the file and create a base RDD
    val inputRDD = sc.textFile("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week9_Apache Spark Introduction/search_data-201008-180523.txt")
    
    //In a Map, No. of inputs and outputs should be exactly same
    //Use flatMap when it is not same
    //separate the words using flat Map
    val words = inputRDD.flatMap(x => x.split(" "))
    
    //Converting the data into Tuples (key, value)
    val wordMap = words.map(x => (x,1))
    
    //Perform aggregation - this is also a transformation
    val finalCount = wordMap.reduceByKey((x,y) => x+y)
    
    //Run action to execute and collect the result
    finalCount.collect.foreach(println)
    
    //In IDE, DAG is only visible until the program runs.
    //In Project Admin Setup HISTORY SERVER to visualize
    //HERE-  - we use below line to make program wait for user input so that DAG can be visualized
    //https://localhost:4040
    scala.io.StdIn.readLine()
    
  //}
}