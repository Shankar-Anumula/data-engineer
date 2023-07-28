package practice.week12spark

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.expr

object dataframeJoinNull extends App{
  
  //setting up spark conf
  val sparkConf = new SparkConf()
  .set("spark.app.name", "Dataframe use case")
  .set("spark.master","local[2]")
  
  //creating a spark session
  val spark = SparkSession.builder()
  .config(sparkConf)
  .getOrCreate()
  
  spark.sparkContext.setLogLevel("ERROR")
  
  //Read the files and load Dataframe
  val ordersDf = spark.read
  .format("csv")
  .option("header",true)
  .option("inferSchema", true)
  .option("path","C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/orders.csv")
  .load()
  
  val customersDf = spark.read
  .format("csv")
  .option("header",true)
  .option("inferSchema", true)
  .option("path","C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/customers.csv")
  .load()
  
  val joinType = "inner"
  val joinCondition = ordersDf.col("customer_id") === customersDf.col("customer_id")
  
  //Below code will lead to ambiguous columns
  //ordersDf.join(customersDf,joinCondition,joinType)
  //.select("order_id","customer_id", "customer_fname")
  //.show()
  
  //Approach#1 - Renaming the column and creating a new DF
  val ordersNewDf = ordersDf.withColumnRenamed("customer_id", "order_customer_id")
  val joinCondition1 = ordersNewDf.col("order_customer_id") === customersDf.col("customer_id")
  
  ordersNewDf.join(customersDf,joinCondition1,joinType)
  .select("order_id","customer_id", "customer_fname")
  .sort("order_id")
  .show()
  
  //Approach#2 - Drop ambiguous column from one of the Dataframes
  ordersDf.join(customersDf,joinCondition,joinType)
  .drop(ordersDf.col("customer_id"))
  .select("order_id","customer_id", "customer_fname")
  .sort("order_id")
  .show()
  
  //Dealing with nulls
  ordersDf.join(customersDf,joinCondition,joinType)
  .drop(ordersDf.col("customer_id"))
  .select("order_id","customer_id", "customer_fname")
  .sort("order_id")
  .withColumn("order_id", expr("coalesce(order_id,-1)"))
  .show()
  
  scala.io.StdIn.readLine()

  //Closing spark session
  spark.stop()
}