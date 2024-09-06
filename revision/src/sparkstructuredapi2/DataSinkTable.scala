package sparkstructuredapi2

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.SaveMode

object DataSinkTable extends App{
  val sparkConf = new SparkConf().setMaster("local[2]").setAppName("Data Sink Table")
  val spark = SparkSession.builder()
  .config(sparkConf)
  .enableHiveSupport() //Enable HIVE Support
  .getOrCreate()
  spark.sparkContext.setLogLevel("ERROR")
  
  val ordersDf = spark.read.format("csv")
  .option("header", true)
  .option("inferSchema", true)
  .option("path", "C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/orders.csv")
  .load()
  
  //orders1 is created by default in default database
  ordersDf.write.format("csv")
  .mode(SaveMode.Append)
  .saveAsTable("orders1")
  
  //create own database
  spark.sql("CREATE DATABASE IF NOT EXISTS retail")
  
  ordersDf.write.format("csv")
  .mode(SaveMode.Append)
  .bucketBy(2, "order_customer_id")
  .saveAsTable("retail.orders1")
  
  spark.catalog.listTables("retail").show()
  spark.catalog.listColumns("retail", "orders1").show()
  
  spark.catalog.listTables("default").show()
  spark.catalog.listColumns("default", "orders1").show()
  
  scala.io.StdIn.readLine()
  spark.stop()

}