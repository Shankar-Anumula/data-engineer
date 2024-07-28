package sparkbasics

import org.apache.spark.SparkContext
import org.apache.log4j.Logger
import org.apache.log4j.Level

object TotalSpent extends App{
    
    Logger.getLogger("org.apache.log4j").setLevel(Level.ERROR)
    //LogManager.getLogger("org.apache.spark").setLevel(Level.ERROR)    
  
    val sc = new SparkContext("local[*]","total spent")
    
    val input = sc.textFile("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week9_Apache Spark Introduction/customerorders-201008-180523.csv")
   
    val spent = input.map(x => (x.split(",")(0), x.split(",")(2).toFloat))
                     .reduceByKey((x,y) => (x+y))
                     .sortBy(x => x._2,false)
                     .collect
    
    //spent.foreach(println)
    
    spent.take(10)
                     
    scala.io.StdIn.readLine()
  
}