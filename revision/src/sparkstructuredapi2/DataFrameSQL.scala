package sparkstructuredapi2

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object DataFrameSQL extends App{
  
  val sparkConf = new SparkConf().setAppName("DataFrame - SQL").setMaster("local[2]")
  val spark = SparkSession.builder().config(sparkConf).getOrCreate()
  spark.sparkContext.setLogLevel("ERROR")
  
  val ordersDf = spark.read.format("csv")
  .option("header", true)
  .option("inferSchema", true)
  .option("path","C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/orders.csv")
  .load()
  
  ordersDf.printSchema()
  //order_id,order_date,order_customer_id,order_status
  
  ordersDf.createOrReplaceTempView("orders")
  
  val resultsDf = spark.sql("select order_status,count(*) as status_count from orders group by order_status order by status_count DESC ")
  
    //Fetch first 20 customers who placed maximum orders
  val resultsDf2 = spark.sql("select order_customer_id, count(*) as total_orders from orders "+
      "where order_status = 'CLOSED' "+
      "group by order_customer_id "+
      "order by total_orders desc")
  
  resultsDf.show()
  resultsDf2.show()
  
  scala.io.StdIn.readLine()
  spark.stop()
  
}