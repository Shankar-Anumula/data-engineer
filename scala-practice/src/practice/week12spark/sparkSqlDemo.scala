package practice.week12spark

import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object sparkSqlDemo extends App{
  Logger.getLogger("org").setLevel(Level.ERROR)
  
  val sparkConf = new SparkConf()
  .setAppName("sparkSQL")
  .setMaster("local[2]")
  
  val spark = SparkSession.builder()
  .config(sparkConf)
  .getOrCreate()
  
  val ordersDf = spark.read
  .format("csv")
  .option("header", true)
  .option("inferSchema",true)
  .option("path","C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/orders.csv")
  .load()
  
  //order_id,order_date,order_customer_id,order_status
  
  ordersDf.createOrReplaceTempView("orders")
  
  //Fetch orders count by status 
  val resultDf = spark.sql("select order_status, count(*) as status_count from orders group by order_status order by status_count desc")
  
  //Fetch first 20 customers who placed maximum orders
  val resultDf2 = spark.sql("select order_customer_id, count(*) as total_orders from orders "+
      "where order_status = 'CLOSED' "+
      "group by order_customer_id "+
      "order by total_orders desc")
  
  //resultDf.show()
  resultDf2.show()
  
  spark.stop()
  
}