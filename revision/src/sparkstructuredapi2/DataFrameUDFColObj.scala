package sparkstructuredapi2

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object DataFrameUDFColObj extends App{
  
  //Creating Spark Session
  val sparkConf = new SparkConf().setAppName("UDF Column Object Demo").setMaster("local[2]")
  val spark = SparkSession.builder().config(sparkConf).getOrCreate()
  spark.sparkContext.setLogLevel("ERROR")
  
  //Defining a UDF
  def ageCheck(age:Int):String = {
    if(age > 18) "Y" else "N"
  }
  
  val dataDf = spark.read
  .format("csv")
  .option("inferSchema", true)
  .option("path", "C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/dataset1")
  .load
  
  val personDf = dataDf.toDF("name","age","city")
  
  //Registering the UDF
  val parseAgeFunction = udf(ageCheck(_:Integer):String)
  
  //Add new column using UDF
  val personDf1 = personDf.withColumn("Adult", parseAgeFunction(col("age")))
  personDf1.printSchema()
  personDf1.show()
  
  //CLose the Spark Session
  scala.io.StdIn.readLine()
  spark.stop()
}