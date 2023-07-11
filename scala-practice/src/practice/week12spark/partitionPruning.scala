package practice.week12spark

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.sql.SaveMode

object partitionPruning extends App{
  Logger.getLogger("org").setLevel(Level.ERROR)
  
  val sparkConf = new SparkConf()
  .setAppName("Spark write Demo")
  .setMaster("local[2]")
  
  val spark = SparkSession.builder()
  .config(sparkConf)
  .getOrCreate()
  
  val ordersDf = spark.read
  .format("csv")
  .option("path", "C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/orders.csv")
  .option("header",true)
  .option("inferSchema",true)
  .load()
  
  ordersDf.write
  .format("avro")
  .partitionBy("order_status")
  .mode(SaveMode.Overwrite)
  .option("maxRecordsPerFile",2500)
  .option("path", "C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/output")
  .save()
  
  
  spark.stop()
  
}