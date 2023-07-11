package practice.week12spark

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.Dataset
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.DateType

object simpleAggregates extends App{
  
  //setting up spark conf
  val sparkConf = new SparkConf()
  .set("spark.app.name", "Dataframe use case")
  .set("spark.master","local[2]")
  
  //creating a spark session
  val spark = SparkSession.builder()
  .config(sparkConf)
  .getOrCreate()
  
  spark.sparkContext.setLogLevel("ERROR")
  
  val invoiceDf = spark.read
  .format("csv")
  .option("header",true)
  .option("inferSchema", true)
  .option("path","C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/order_data.csv")
  .load()
  
  //Simple aggregates using column objects
  
  invoiceDf.select(count("*").as("RowCount"), 
                   sum("Quantity").as("totalQuantity"),
                   avg("UnitPrice").as("AvgPrice"),
                   countDistinct("InvoiceNo").as("UniqueInvoices")
                   ).show()
                   
  //Simple aggregates using the String expression
  
  invoiceDf.selectExpr(
      "count(StockCode) as RowCount",
      "sum(Quantity) as totalQuantity",
      "avg(UnitPrice) as AvgPrice",
      "count(Distinct(InvoiceNo)) as UniqueInvoices"
      ).show()
                   
  //Simple aggregates using the Spark SQL
      
  invoiceDf.createOrReplaceTempView("sales")
  
  spark.sql("select count(*) as RowCount, sum(Quantity) as TotalQuantity, avg(UnitPrice) as AvgPrice, count(Distinct(InvoiceNo)) as UniqueInvoices from sales")
  .show()
  
  
  
  //stopping spark session
  spark.stop()
  
}