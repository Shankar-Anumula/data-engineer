import org.apache.spark.SparkContext
import org.apache.log4j.Level
import org.apache.log4j.Logger


object WordCount extends App{
  
  //def main(args:Array[String]){
    
    //setting to Logging level to show ERROR, and ignore general informational messages
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    //In IDE, spark context is not available so we need to create it. Use Local CPU cores, and show the application as wordcount
    val sc = new SparkContext("local[*]","wordcount")
    
    
    //Load the file and create a base RDD
    val input = sc.textFile("C:/All_WorkSpace/Data Engineering/Trendy Tech/Week9_Apache Spark Introduction/search_data-201008-180523.txt")
    
    //seperate the words using flatMap
    val words  =input.flatMap(x => x.split(" "))
    
    //converting the data into Tuples (2 record - key, value)
    val wordMap = words.map(x => (x,1))
    
    //Perform aggregation - this is a transformation only
    val finalCount = wordMap.reduceByKey((x,y) => x+y)
    
    //Run Action to execute and collect the result
    val result  = finalCount.collect.foreach(println)
    
    //make the program wait for user input so that DAG can be visualized in https://localhost:4040
    scala.io.StdIn.readLine()
    
  //}
    
}