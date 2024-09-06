package sparkstructuredapi2

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.Dataset
import org.apache.spark.sql.functions._
import org.apache.spark.sql.Row

case class Person(name:String, age:Integer, city:String)

object DataFrameUDFColObj extends App{
  val sparkConf = new SparkConf().setAppName("DataFrame Reader").setMaster("local[2]")
  val spark = SparkSession.builder().config(sparkConf).getOrCreate()
  spark.sparkContext.setLogLevel("ERROR")
  
  /*Datasets CSV - C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/dataset1
   */
  val dataDf = spark.read
                      .format("csv")
                      .option("inferSchema",true)
                      .option("path","C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/dataset1")
                      .load
  
  //Conversions
  val namesDf:Dataset[Row] = dataDf.toDF("name","age","city")
  import spark.implicits._
  val personDs = namesDf.as[Person]                    
  val names2Df = personDs.toDF()
  
  //UDF Function for checking age
  def ageCheck(age:Int):String = {
    if(age>18) "Y"
    else "N"
  }
  
  //Registering a UDF Function - age Check
  val parseAge = udf(ageCheck(_:Int):String)
  
  //val person2Ds = personDs.withColumn("Adult", parseAge(col("Age")))
  //person2Ds.show()
  
  val personDf = namesDf.withColumn("adult", parseAge(col("Age")))
  personDf.show()
  
  scala.io.StdIn.readLine()
  spark.stop()
  //
  
}