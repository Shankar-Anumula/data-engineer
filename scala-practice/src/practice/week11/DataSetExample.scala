package practice.week11

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.sql.Dataset
import org.apache.spark.sql.Row
import java.sql.Timestamp

case class OrdersData (order_id: Integer, order_date: Timestamp, order_customer_id: Integer, order_status:String)

object DataSetExample extends App{

  Logger.getLogger("org").setLevel(Level.ERROR)
  
  val sparkConf = new SparkConf()
  
  sparkConf.set("spark.app.name", "my first application")
  sparkConf.set("spark.master","local[2]")
  
  val spark  = SparkSession.builder()
  .config(sparkConf)
  .getOrCreate()
  
  //Data frame is a data set of row type
  val ordersDf:Dataset[Row] = spark.read
  .option("header", true)
  .option("inferSchema",true)
  .csv("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week11_Spark Structured API - Part1/orders-201019-002101.csv")
  
  //below line throws a run time error - order_ids doesnt exist
  //ordersDf.filter("order_ids < 10").show
  
  //import indicates the "spark" from spark session created and should be imported after creating spark session
  import spark.implicits._
  
  val ordersDs = ordersDf.as[OrdersData]
  
  //Dataset - below line again throws a run time error - order_ids doesnt exist
  //ordersDs.filter("order_ids < 10").show
  
  //Below statement gives compile time safety
  //Can we not use the same for Dataframe? No, the x in anonymous functions doesnt resolve to a type at compile time
  ordersDs.filter(x => x.order_id < 10).show
  
  //Emitting Log Message
  Logger.getLogger(getClass.getName).info("My Application completed successfully")
  
  scala.io.StdIn.readLine()
  spark.stop()
  
}