package sparkstructuredapi2

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.types.DoubleType
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.IntegerType
import org.apache.spark.sql.types.StringType

object DataFrameAggregratesWindow extends App{
  //order_data
  //Creating Spark Session
  val sparkConf = new SparkConf().setAppName("UDF Column Object Demo").setMaster("local[2]")
  val spark = SparkSession.builder().config(sparkConf).getOrCreate()
  spark.sparkContext.setLogLevel("ERROR")
  
  val schema = StructType(List(
      StructField("country",StringType),
      StructField("weeknum",IntegerType),
      StructField("noOfInvoices",IntegerType),
      StructField("totalquantity",DoubleType),
      StructField("invoicevalue",DoubleType)
    ))
  
  val invoicesDf = spark.read.format("csv")
  .schema(schema)
  .option("path", "C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/windowdata.csv")
  .load
  
  invoicesDf.printSchema()
  
  //Window Aggregates - Define the window
          // 1. Partition column
          // 2. Ordering COlumn
          // 3. The window size
  val myWindow = Window.partitionBy("country")
  .orderBy("weeknum")
  .rowsBetween(Window.unboundedPreceding, Window.currentRow)
  
  val myWindow2 = Window.partitionBy("country")
  .orderBy("weeknum")
  .rowsBetween(-1, Window.currentRow)
  
  val myWindow3 = Window.partitionBy("country")
  .orderBy("weeknum")
  .rowsBetween(Window.unboundedPreceding, Window.unboundedFollowing)
  
  //Use the window defined
  invoicesDf.withColumn("RunningTotal", sum("invoicevalue").over(myWindow))
  .withColumn("Prev1CurrTotal", sum("invoicevalue").over(myWindow2))
  .withColumn("CompleteTotal", sum("invoicevalue").over(myWindow3))
  .show()
   

   //Close the Spark Session
  scala.io.StdIn.readLine()
  spark.stop()

}