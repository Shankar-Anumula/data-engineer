package assignments.spark

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.types.StringType
import org.apache.spark.sql.types.IntegerType
import org.apache.spark.sql.SaveMode
import org.apache.spark.sql.types.DoubleType

case class windowData(Country: String, Weeknum: Int, NumInvoices: Int, TotalQuantity: Int, InvoiceValue: Double)

object Assignment3 extends App{
  
  /*
   * Problem 1: We have a file windowdata.csv and the field names are country, weeknum, numinvoices, totalquantity, invoicevalue
   * Step 1: create spark session
   * Step 2: set the logging level to error
   * Step 3:   Using the standard dataframe reader API load the file and create a dataframe. Note: The schema should be provided using StructType (do not use infer schema)
   * Step 4:   Use the standard dataframe writer api to save it in parquet format. While saving make sure data is stored where we should have a folder for each country, weeknum (combination)
   * Step 5:  Also use the dataframe write api to save the data in Avro format. While saving make sure data is stored where we should have a folder for each country.

	 * Problem 2: 
	 * step 1: Create spark session
	 * step 2: Set the logging level to error
	 * step 3: Load the data file windowdata.csv as a rdd
	 * step 4: Create a dataframe from this RDD by defining case class
	 * step 5: Save this dataframe in JSON format in 8 files
   */
  
  val sparkConf = new SparkConf().setAppName("Week11 Assignments").setMaster("local[2]")
  val spark = SparkSession.builder().config(sparkConf).getOrCreate()
  spark.sparkContext.setLogLevel("ERROR")
  
  val windowsSchema = StructType(List(
      StructField("country", StringType),
      StructField("weeknum", IntegerType),
      StructField("numinvoices", IntegerType),
      StructField("totalquantity", IntegerType),
      StructField("invoicevalue", DoubleType)
      ))
      
  val windowsDf = spark.read
                       .format("csv")
                       .option("header", false)
                       .schema(windowsSchema)
                       .option("path", "C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week11_Spark Structured API - Part1/Assignments/windowdata-201021-002706.csv")
                       .load
   
   //Write data in Parquet format to folder for each country and weeknum combination
   windowsDf.write
   .format("parquet")
   .mode(SaveMode.Overwrite)
   .option("path", "C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week11_Spark Structured API - Part1/Assignments/Output-Parquet/")
   .partitionBy("country","weeknum")
   .save()
   
    /*Write data in Avro format to folder for each country
      windowsDf.write
   .format("avro")
   .mode(SaveMode.Overwrite)
   .option("path", "C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week11_Spark Structured API - Part1/Assignments/Output-avro/")
   .partitionBy("country")
   .save()*/
   
   import spark.implicits._
   val windowDataDF = spark.sparkContext.textFile("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week11_Spark Structured API - Part1/Assignments/windowdata-201021-002706.csv")
   .map(x => x.split(","))
   .map(x => windowData(x(0).trim(),x(1).trim().toInt,x(2).trim().toInt,x(3).trim().toInt,x(4).trim().toDouble))
   .toDF()
   .repartition(8)
   
  
   windowDataDF.write
   .format("json")
   .mode(SaveMode.Overwrite)
   .option("path", "C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week11_Spark Structured API - Part1/Assignments/Output-Json/")
   .save()
   
   scala.io.StdIn.readLine()
  spark.stop()
}