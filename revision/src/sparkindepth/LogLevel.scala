package sparkindepth

import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.SparkContext

object LogLevel extends App{
  
  Logger.getLogger("org").setLevel(Level.ERROR)
  
  /*
   * Dataset : List 
   
   * KEYWORDS: PARALLELIZE
   * STEP1: Create a List and parallelize as per default parallelism
   * STEP2: Create a tuple using map
   * STEP3: count the "WARN" and "ERROR" using reduceByKey
   * STEP4: Collect the output
   */
  
  
  val sc = new SparkContext("local[*]","log level")
  
  val myList= List("WARN: Tuesday 4 September 0405",
                    "ERROR: Tuesday 4 September 0408",
                    "ERROR: Tuesday 4 September 0408",
                    "ERROR: Tuesday 4 September 0408",
                    "ERROR: Tuesday 4 September 0408",
                    "ERROR: Tuesday 4 September 0408")
  
  //Traditional Approach
  //val inputRDD = sc.parallelize(myList)
  //val mappedInput = inputRDD.map(x => (x.split(":")(0),1))
  //val finalOutput = mappedInput.reduceByKey((x,y) => (x+y))
  //finalOutput.collect().foreach(println)
  
  //Chaining up functions
  sc.parallelize(myList)
  .map(x => (x.split(":")(0),1))
  .reduceByKey((x,y) => (x+y))
  .collect.foreach(println)
  
  
  scala.io.StdIn.readLine()
                    
                    
                    
                    
  
}