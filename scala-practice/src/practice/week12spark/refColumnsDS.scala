package practice.week12spark

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

import org.apache.spark.sql.functions._

object refColumnsDS extends App{
  val sparkConf = new SparkConf()
  .setAppName("Data Table")
  .setMaster("local[2]")
  
  val spark = SparkSession.builder()
  .config(sparkConf)
  .getOrCreate()
  
  //Set log level at spark
  spark.sparkContext.setLogLevel("ERROR")
  
  val ordersDf = spark.read
  .format("csv")
  .option("header", true)
  .option("inferSchema",true)
  .option("path","C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/orders.csv")
  .load()
  
  //1. Column string - columns are specified in string format
  ordersDf.select("order_id","order_status").show()
  
  //2. Column Object - uses column and col methods
  ordersDf.select(column("order_id"),col("order_status")).show()
  
  //2. Column Object - scala specific syntax $ and '
  
  import spark.implicits._
  ordersDf.select($"order_id",'order_status).show()
  
  //2. Column Object - use a combination of all object types
  ordersDf.select(column("order_id"),col("order_date"),$"order_customer_id",'order_status).show()
  
  //3. Column expression using expr
  ordersDf.select(column("order_id"),col("order_date"),expr("concat(order_status,'_STATUS')")).show(false)
  
  //3. Column Expression using selectExpr
  ordersDf.selectExpr("order_id","order_date","concat(order_status,'_STATUS')").show(false)
  
   

  
  spark.stop()
}