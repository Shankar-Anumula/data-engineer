package practice.week11

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object DataFrameReader extends App{
  
  val sparkConf = new SparkConf()
  sparkConf.set("spark.app.name", "e2e processing")
  sparkConf.set("spark.master","local[2]")
  
  val spark = SparkSession.builder()
  .config(sparkConf)
  .getOrCreate()
  
  // CSV 
  
  val ordersDf = spark.read
  .format("csv")
  .option("header", true)
  .option("inferSchema",true)
  .option("path", "C:/All_WorkSpace/Data Engineering/Trendy Tech/Week11_Spark Structured API - Part1/orders-201019-002101.csv")
  .load
    
  ordersDf.printSchema()
  ordersDf.show()
  
  //JSON
    //Deal with malformed record
    //mode - PERMISSIVE, DROPMALFORMED, FAILFAST
    //change truncate to false in show for detailed info

  val playersDf = spark.read
  .format("json")
  .option("path", "C:/All_WorkSpace/Data Engineering/Trendy Tech/Week11_Spark Structured API - Part1/players-201019-002101.json")
  .option("mode","DROPMALFORMED")
  .load
  
  playersDf.printSchema()
  playersDf.show(false)
  
  
  //PARQUET
  val usersDf = spark.read
  .option("path", "C:/All_WorkSpace/Data Engineering/Trendy Tech/Week11_Spark Structured API - Part1/users-201019-002101.parquet")
  .load
  
  usersDf.printSchema()
  usersDf.show()
  
}