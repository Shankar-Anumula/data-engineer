package practice.week12spark

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.Dataset
import org.apache.spark.sql.Row

object udfPersonDemo extends App {
  
  case class Person(name:String,age:Integer,city:String)
  
  //Function for checking age 
  def ageCheck(age:Integer):String = {
    if(age>18) "Y" else "N"
  }
  
  val sparkConf = new SparkConf()
  .setAppName("Data Table")
  .setMaster("local[2]")
  
  val spark = SparkSession.builder()
  .config(sparkConf)
  .getOrCreate()
  
  //Set log level at spark
  spark.sparkContext.setLogLevel("ERROR")
  
  val df = spark.read
  .format("csv")
  .option("header", true)
  .option("inferSchema",true)
  .option("path","C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/dataset1")
  .load()
    
  val df1:Dataset[Row] = df.toDF("name","age","city")
  //df1.printSchema()
  
  //Registering the UDF function - ageCheck
  val parseAgeFunction = udf(ageCheck(_:Integer):String)
  
  val df2 = df1.withColumn("adult", parseAgeFunction(column("age")))
    
  df2.show()
  
  spark.catalog.listFunctions().filter(x => x.name == "parseAgeFunction").show()

  
  
  spark.stop()
  
}