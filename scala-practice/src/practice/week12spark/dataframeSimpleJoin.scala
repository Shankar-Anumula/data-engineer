package practice.week12spark

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object dataframeSimpleJoin extends App{
  
  //setting up spark conf
  val sparkConf = new SparkConf()
  .set("spark.app.name", "Dataframe use case")
  .set("spark.master","local[2]")
  
  //creating a spark session
  val spark = SparkSession.builder()
  .config(sparkConf)
  .getOrCreate()
  
  spark.sparkContext.setLogLevel("ERROR")
  
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
  
  //Join condition
  val joinCondition = ordersDf.col("order_customer_id") === customersDf.col("customer_id")
  
  //Join type
  val joinType1 = "inner"
  val joinType2 = "outer"
  val joinType3 = "right"
  val joinType4 = "Left"
  
  //Simple Join
  ordersDf.join(customersDf,joinCondition,joinType1).sort("customer_id").show()
  ordersDf.join(customersDf,joinCondition,joinType2).show()
  ordersDf.join(customersDf,joinCondition,joinType3).show()
  ordersDf.join(customersDf,joinCondition,joinType4).show()

  //Closing spark session
  spark.stop()
  
}