package practice.week12spark

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object groupingAggregates extends App{
  
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
  
  //Grouping aggregates using column object
  
  invoiceDf.groupBy("country", "invoiceNo")
  .agg(sum("Quantity").as("TotalQuantity"),
      sum(expr("Quantity * UnitPrice")).as("InvoiceValue")
      ).show()
  
  //Grouping aggregates using String expression
  invoiceDf.groupBy("country", "invoiceNo")
  .agg(expr("sum(Quantity) as TotalQuantity"),
      expr("sum(Quantity * UnitPrice) as InvoiceValue")
      ).show()
  
  //Grouping aggregates using spark SQL expression
      
  invoiceDf.createOrReplaceTempView("sales")    
  spark.sql("""select country, invoiceNo, sum(Quantity) AS TotalQuantity, sum(Quantity*UnitPrice) as InvoiceValue 
               from sales 
                group by country, invoiceNo"""
      ).show()
      
      
  //stopping spark session
  spark.stop()
  
}