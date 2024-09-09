package sparkstructuredapi2

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._


object DataFrameAggregatesGroup extends App{
  //order_data
  //Creating Spark Session
  val sparkConf = new SparkConf().setAppName("UDF Column Object Demo").setMaster("local[2]")
  val spark = SparkSession.builder().config(sparkConf).getOrCreate()
  spark.sparkContext.setLogLevel("ERROR")
  
  val invoicesDf = spark.read.format("csv")
  .option("inferSchema", true)
  .option("header", true)
  .option("path", "C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/order_data.csv")
  .load
  
  invoicesDf.printSchema()
  
  //1. Group Aggregates using COlumn Object Expression
  
  invoicesDf.groupBy("Country", "InvoiceNo")
  .agg(
        sum("Quantity").as("TotalQuantity"),
        sum(expr("Quantity * UnitPrice")).as("InvoiceValue")
       ).show()
      
   //2. Group Aggregates using String Expression
   invoicesDf.groupBy("Country", "InvoiceNo")
  .agg(
        expr("sum(Quantity) as TotalQuantity"),
        expr("sum(Quantity * UnitPrice) as InvoiceValue")
       ).show()
       
    //3. Group Aggregates using spark SQL   
   invoicesDf.createOrReplaceTempView("Invoices")
   
   spark.sql("Select sum(Quantity) as TotalQuantity, sum(Quantity * UnitPrice) as InvoiceValue from Invoices group by Country, InvoiceNo").show()
  //Close the Spark Session
  scala.io.StdIn.readLine()
  spark.stop()


}