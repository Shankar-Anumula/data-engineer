package assignments.week11spark

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.StringType
import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.types.IntegerType
import org.apache.spark.sql.types.FloatType

object structuredapi1 extends App{
  
  Logger.getLogger("org").setLevel(Level.ERROR)
  
  val sparkConf = new SparkConf()
  .setAppName("Week11 Assignment")
  .setMaster("local[*]")
  
  val spark = SparkSession.builder()
  .config(sparkConf)
  .getOrCreate()
  
  val windowSchema = StructType(List(
        StructField("Country",StringType),
      StructField("weeknum",IntegerType),
      StructField("numinvoices",IntegerType),
      StructField("totalquantity",IntegerType),
      StructField("invoicevalue",FloatType)
      ))
    
  val windowDf = spark.read
  .format("csv")
  .option("header", true)
  .option("inferSchema",true)
  .option("path","C:/All_WorkSpace/Data Engineering/Trendy Tech/Week11_Spark Structured API - Part1/windowdata-201021-002706.csv")
  .schema(windowSchema)
  .load
  
  windowDf.printSchema()
  windowDf.show()
  
  spark.stop()
  
  
  
  
  
}