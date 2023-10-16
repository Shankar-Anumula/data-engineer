package practice.week18aws1

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object Students extends App{
  
  //setting up spark Conf
  val sparkConf = new SparkConf()
  .setAppName("students application")
  .setMaster("local[2]")
  
  
  //create spark session
  val spark = SparkSession.builder()
  .config(sparkConf)
  .getOrCreate()
  
  //reading the students data
  val studentsDf = spark.read
  .format("csv")
  .option("inferSchema", true)
  .option("header", true)
  .option("path", "C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week18_Big Data on Cloud Part-1/students_with_header-201214-111236.csv")
  .load
  
  
  studentsDf.write.partitionBy("subject").parquet("C:/All_WorkSpace/Data-Engineering/students_output_parquet")
     
  scala.io.StdIn.readLine()
  spark.stop()
  
}