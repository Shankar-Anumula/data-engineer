package sparkstructuredapi

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object DataFrameReader extends App{
  
  val sparkConf = new SparkConf().setAppName("DataFrame Reader").setMaster("local[2]")
  val spark = SparkSession.builder().config(sparkConf).getOrCreate()
  spark.sparkContext.setLogLevel("ERROR")
  
  /*Datasets
    CSV - orders-201019-002101.csv
    JSON - players-201019-002101.json
    Parquet - users-201019-002101.parquet
   */
  
  //CSV - format, header and inferSchema provided
  val ordersDf = spark.read
                      .format("csv")
                      .option("header", true)
                      .option("inferSchema",true)
                      .option("path","C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week11_Spark Structured API - Part1/orders-201019-002101.csv")
                      .load
                      
  ordersDf.printSchema()
  ordersDf.show()
  
  //JSON - format.  header and inferSchema not required (default inferred schema)
  val playersDf = spark.read
                      .format("json")
                      .option("path","C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week11_Spark Structured API - Part1/players-201019-002101.json")
                      .load
                      
  playersDf.printSchema()
  playersDf.show()
  
  //Parquet - format, header and inferSchema not required (default format)
  val usersDf = spark.read
                      .option("path","C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week11_Spark Structured API - Part1/users-201019-002101.parquet")
                      .load
                      
  usersDf.printSchema()
  usersDf.show()
  
  
  scala.io.StdIn.readLine()
  spark.stop()
  
}