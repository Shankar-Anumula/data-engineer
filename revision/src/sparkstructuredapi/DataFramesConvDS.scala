package sparkstructuredapi

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.Dataset
import org.apache.spark.sql.Row
import java.sql.Timestamp

case class OrdersData(order_id:Int,order_date:Timestamp,order_customer_id:Int,order_status:String)

object DataFramesConvDS extends App{
  
  val sparkConf = new SparkConf()
                                .setAppName("DF to DS Converter")
                                .setMaster("local[2]")
  val spark = SparkSession.builder()
                          .config(sparkConf)
                          .getOrCreate()
  val ordersDf:Dataset[Row] = spark.read
  .option("header", true)
  .option("inferSchema",true)
  .csv("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week11_Spark Structured API - Part1/orders-201019-002101.csv")
  
  //below line throws a run time error - order_ids doesnt exist
  //ordersDf.filter("order_ids < 10").show()
  
  //import indicates the "spark" from spark session created and should be imported after creating spark session
  import spark.implicits._
  val ordersDs:Dataset[OrdersData] = ordersDf.as[OrdersData]
  
  /* 
   * Dataset - below line again throws a run time error - order_ids doesnt exist
  	 ordersDs.filter("order_ids < 10").show
  
     Below statement gives compile time safety
     Can we not use the same for Dataframe? No, the x in anonymous functions doesnt resolve to a type at compile time
   */
  ordersDs.filter(x => x.order_id <10).show()
  
  scala.io.StdIn.readLine()
  spark.stop()
  
}