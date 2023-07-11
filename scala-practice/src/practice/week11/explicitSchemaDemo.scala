package practice.week11

import java.sql.Timestamp

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.IntegerType
import org.apache.spark.sql.types.StringType
import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.TimestampType

case class Orders(ordid: Integer, orddate:Timestamp, custid :Integer, ordstatus :String)

object explicitSchemaDemo extends App{
  
  val sparkConf = new SparkConf()
  .setAppName("Explicit Schema Demo")
  .setMaster("local[2]")

  val spark = SparkSession.builder()
  .config(sparkConf)
  .getOrCreate()
  
  //Explicit - Programmatic approach using spark data types
  
  val ordersSchema = StructType(
      List(
      	StructField("orderid",IntegerType),
      	StructField("orderdate",TimestampType),
      	StructField("customerid",IntegerType),
      	StructField("status",StringType)
     	)
    )
    
  val ordersDf = spark.read
  .format("csv")
  .option("header", true)
  //.option("inferSchema", true)
  .option("path", "C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week11_Spark Structured API - Part1/orders-201019-002101.csv")
  .schema(ordersSchema)
  .load
  
  ordersDf.printSchema()
  ordersDf.show()
  
  //Explicit - DDL Approach using scala data types
  
  val ordersSchemaDDL = "orderid Int, orderdate String, custid Int, ordstatus String"
  
  val ordersDDLDf = spark.read
  .format("csv")
  .option("header", true)
  //.option("inferSchema", true)
  .option("path", "C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week11_Spark Structured API - Part1/orders-201019-002101.csv")
  .schema(ordersSchemaDDL)
  .load
  
  ordersDDLDf.printSchema()
  ordersDDLDf.show()
  

  
  //Converting to dataset using case class
  //import is very important
  
  import spark.implicits._
  
  val ordersDs = ordersDDLDf.as[Orders]
  
  ordersDs.printSchema()
  ordersDs.show()
    
  scala.io.StdIn.readLine()
  spark.stop()
}