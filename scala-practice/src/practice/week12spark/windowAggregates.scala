package practice.week12spark

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._

object windowAggregates extends App{
  
  //setting up spark conf
  val sparkConf = new SparkConf()
  .set("spark.app.name", "Dataframe use case")
  .set("spark.master","local[2]")
  
  //creating a spark session
  val spark = SparkSession.builder()
  .config(sparkConf)
  .getOrCreate()
  
  spark.sparkContext.setLogLevel("ERROR")
  
  val DDLString = "country String, weeknum Integer,numinvoices Integer,totalQuantity Integer,invoiceValue Double"
  
  val invoiceDf = spark.read
  .format("csv")
  .option("header",false)
  //.option("inferSchema", true)
  .option("path","C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/windowdata.csv")
  .schema(DDLString)
  .load()
  
  invoiceDf.printSchema()
  
  
  val myWindow = Window.partitionBy("country")
  .orderBy("weeknum")
  .rowsBetween(Window.unboundedPreceding, Window.currentRow)
  
  invoiceDf.withColumn("RunningTotal", sum("invoicevalue")
      .over(myWindow))
      .show()
      
  val myWindow2 = Window.partitionBy("country")
  .orderBy("weeknum")
  .rowsBetween(Window.unboundedPreceding, Window.unboundedFollowing)
  
  invoiceDf.withColumn("RunningTotal", sum("invoicevalue")
      .over(myWindow2))
      .show()
  
  //stopping spark session
  spark.stop()
  
}