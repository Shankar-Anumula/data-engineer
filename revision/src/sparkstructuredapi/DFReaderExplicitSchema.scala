package sparkstructuredapi

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.types.IntegerType
import org.apache.spark.sql.types.TimestampType
import org.apache.spark.sql.types.StringType
import java.sql.Timestamp

case class Orders(ordid: Integer, orddate:Timestamp, custid :Integer, ordstatus :String)

object DFReaderExplicitSchema extends App{
  val sparkConf = new SparkConf().setAppName("DataFrame Reader").setMaster("local[2]")
  val spark = SparkSession.builder().config(sparkConf).getOrCreate()
  spark.sparkContext.setLogLevel("ERROR")
  
  //CSV - format, header and inferSchema provided
  val ordersDf = spark.read
                      .format("csv")
                      .option("header", true)
                      .option("inferSchema",true)
                      .option("path","C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week11_Spark Structured API - Part1/orders-201019-002101.csv")
                      .load
  ordersDf.printSchema()
  ordersDf.show()
  
   /* EXPLICIT SCHEMA APPROACHES
    * 1. Programatic approach using STRUCT - uses Spark data types
    * 2. DDL string - uses scala data types
    */
  
  //Approach1 - Explicit - Programmatic approach using spark data types
  val ordersSchema = StructType(List(
        StructField("orderid",IntegerType),
        StructField("orderdate",TimestampType),
        StructField("customerid",IntegerType),
        StructField("status",StringType)
        ))
    
  val ordersDf1 = spark.read
                      .format("csv")
                      .option("header", true)
                      .schema(ordersSchema)
                      .option("path","C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week11_Spark Structured API - Part1/orders-201019-002101.csv")
                      .load
  
  ordersDf1.printSchema()
  ordersDf1.show()
  
  //Approach2 - Explicit - DDL Approach using scala data types
  val ordersSchemaDDL = "orderid Int, orderdate String,custid Int, ordstatus String" 
  val ordersDf2 = spark.read
                      .format("csv")
                      .option("header", true)
                      .schema(ordersSchemaDDL)
                      .option("path","C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week11_Spark Structured API - Part1/orders-201019-002101.csv")
                      .load
  
  ordersDf2.printSchema()
  ordersDf2.show()
  
  //Converting to Dataset using case class and import spark implicits
  import spark.implicits._
  val ordersDs = ordersDf.as[Orders]
  
  ordersDs.printSchema()
  ordersDs.show()
  
  scala.io.StdIn.readLine()
  spark.stop()  
}