package practice.week12spark

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.Dataset
import org.apache.spark.sql.Row

object sqlExprUDFDemo extends App{
  
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
  
  //Registering the SQL UDF function
  spark.udf.register("parseAgeFunction", ageCheck(_:Integer):String)
  //val df2 = df1.withColumn("adult", expr("parseAgeFunction(age)"))
  
  //df2.show()
  
  //using anonymous function
  //spark.udf.register("parseAgeFunction", (x:Integer) => {if (x>18) "Y" else "N"})
  //val df3 = df1.withColumn("adult", expr("parseAgeFunction(age)"))

  //df3.show()
  
  //SQL UDFs are registered in catalog, so we convert to view and use SQL
  spark.catalog.listFunctions().filter(x => x.name == "parseAgeFunction").show()
  
  df1.createOrReplaceTempView("peopleTable")
  spark.sql("select name, age, city, parseAgeFunction(age) as adult from peopleTable").show
  
  spark.stop()
  
  
  
  
}