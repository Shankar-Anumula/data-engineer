import org.apache.spark.SparkContext
import org.apache.log4j.Level
import org.apache.log4j.Logger


object WordCount3 extends App{
  //def main(args:Array[String]){
    
    //setting to Logging level to show ERROR, and ignore general informational messages
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    //In IDE, spark context is not available so we need to create it. Use Local CPU cores, and show the application as wordcount
    //Load the file and create a base RDD
    //separate the words using flatMap
    //converting the data into Tuples (2 record - key, value)
    //Perform aggregation - this is a transformation only
    //Run Action to execute and collect the result
    val sc = new SparkContext("local[*]","wordcount")
    
    val results = sc.textFile("C:/All_WorkSpace/Data Engineering/Trendy Tech/Week9_Apache Spark Introduction/search_data-201008-180523.txt")
                .flatMap(_.split(" "))
                .map(_.toLowerCase())
                .map((_,1))
                .reduceByKey(_+_)
                .sortBy(x=>x._2,false)
                .collect
     
                for (result <- results){
                  val word = result._1
                  val count = result._2.toInt
                  
                  if(count > 10)
                    println(s"$word:$count")
                  
                }
       
    
    //make the program wait for user input so that DAG can be visualized in https://localhost:4040
    scala.io.StdIn.readLine()
    
  //}
}