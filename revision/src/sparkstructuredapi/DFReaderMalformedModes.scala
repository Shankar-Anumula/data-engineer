package sparkstructuredapi

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object DFReaderMalformedModes extends App{
  
  val sparkConf = new SparkConf().setAppName("DataFrame Reader").setMaster("local[2]")
  val spark = SparkSession.builder().config(sparkConf).getOrCreate()
  spark.sparkContext.setLogLevel("ERROR")
  
  //JSON - format.  header and inferSchema not required (default inferred schema)
  
  /* MALFORMED RECORDS : Modes for dealing with malformed records
     * 1. PERMISSIVE (Default) - shows NULLs in all columns and moves entire data to _corrupt_record
     * 2. DROPMALFORMED - will ignore and remove the malformed record
     * 3. FAILFAST - Exception is thrown when malformed record is encountered
   */
  
  val playersDf = spark.read
                      .format("json")
                      .option("path","C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week11_Spark Structured API - Part1/players-201019-002101.json")
                      //.option("mode", "PERMISSIVE") //DEFAULT - no need to specify
                      //.option("mode", "DROPMALFORMED")
                      //.option("mode", "FAILFAST")
                      .load
                      
  playersDf.printSchema()
  playersDf.show(false)
  
  scala.io.StdIn.readLine()
  spark.stop()
  
}