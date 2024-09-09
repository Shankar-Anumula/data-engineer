package sparkstructuredapi2

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object DataFrameAggregatesSimple extends App{
  
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
  
  //1. Simple Aggregates using COlumn Object Expression
  invoicesDf.select(
      count("*").as("Total Number of Rows"),
      sum("Quantity").as("Total Quantity"),
      avg("UnitPrice").as("Average Unit Price"),
      countDistinct("invoiceNo").as("Number of Unique Invoices")
      ).show()
      
   //2. Simple Aggregates using String Expression
   invoicesDf.selectExpr(
      "count(StockCode) as RowCount",
      "sum(Quantity) as TotalQuantity",
      "avg(UnitPrice) as AverageUnitPrice",
      "count(Distinct(invoiceNo)) as UniqueInvoices"
      ).show()
      
    //3. Simple Aggregates using spark SQL   
    invoicesDf.createOrReplaceTempView("Invoices") 
    spark.sql("select count(*) as RowCount, sum(Quantity) as TotalQuantity, avg(UnitPrice) as AverageUnitPrice, count(Distinct(invoiceNo)) as UniqueInvoices from Invoices").show()
      
  //Close the Spark Session
  scala.io.StdIn.readLine()
  spark.stop()

}