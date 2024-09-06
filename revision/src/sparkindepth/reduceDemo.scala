package sparkindepth

import org.apache.spark.SparkContext
import org.apache.log4j.Level
import org.apache.log4j.Logger

object reduceDemo extends App{
  Logger.getLogger("org").setLevel(Level.ERROR)
  
  /*
   * Dataset : List 
   
   * KEYWORDS: PARALLELIZE, REDUCE
   * STEP1: Create a Range of elements
   * STEP2: Count the output using reduce
   * STEP4: Collect the output
   */
  
  
  val sc = new SparkContext("local[*]","log level")
  
  val myList = 1 to 100
  
  //Chaining up functions
  val output = sc.parallelize(myList)
                 .reduce((x,y) => (x+y))
  
  println(output)
  
  scala.io.StdIn.readLine()
}