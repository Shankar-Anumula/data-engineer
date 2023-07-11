package practice.week12spark

import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.SaveMode

object dataSinkTable extends App{
  
  Logger.getLogger("org").setLevel(Level.ERROR)
  
  val sparkConf = new SparkConf()
  .setAppName("Data Table")
  .setMaster("local[2]")
  
  val spark = SparkSession.builder()
  .config(sparkConf)
  .enableHiveSupport() //import spark-hive jar.. this indicates we will use hive for metadata support
  .getOrCreate()
  
  val ordersDf = spark.read
  .format("csv")
  .option("header", true)
  .option("inferSchema",true)
  .option("path","C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/orders.csv")
  .load()
  
  //orders1 is created by default in default database
  ordersDf.write
  .format("csv")
  .mode(SaveMode.Overwrite)
  .saveAsTable("orders1")
  
  //create own database
  spark.sql("create database if not exists retail")
  
  ordersDf.write
  .format("csv")
  .mode(SaveMode.Overwrite)
  .bucketBy(4, "order_customer_id")
  .saveAsTable("retail.orders1")
  
  spark.catalog.listTables("retail").show()
  
  
  
  spark.stop()
  
}