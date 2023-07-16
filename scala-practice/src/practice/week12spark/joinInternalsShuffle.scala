package practice.week12spark

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object joinInternalsShuffle extends App{
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
  
  //Outer - simple join - shuffle sort merge join
  //inner - broadcast join because of system optimization and smaller datasets
  //right and left - also broadcast join in this case
  
  //avoid system optimization
  //spark.sql("SET spark.sql.autoBroadcastJoinThreshold = -1")
  
  val joinType = "inner"
  val joinCondition = ordersDf.col("customer_id") === customersDf.col("customer_id")
  
  //ordersDf.join(customersDf,joinCondition,joinType) //Simple join
  ordersDf.join(broadcast(customersDf),joinCondition,joinType)  //Broadcast join
  .drop(ordersDf.col("customer_id"))
  .select("order_id","customer_id", "customer_fname")
  .sort("order_id")
  .show()
  
  scala.io.StdIn.readLine()

  //Closing spark session
  spark.stop()
}