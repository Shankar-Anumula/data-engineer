package practice.week12spark

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.Dataset
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.DateType

object dataFramesUseCase extends App{
    
  //setting up spark conf
  val sparkConf = new SparkConf()
  .set("spark.app.name", "Dataframe use case")
  .set("spark.master","local[2]")
  
  //creating a spark session
  val spark = SparkSession.builder()
  .config(sparkConf)
  .getOrCreate()
  
  spark.sparkContext.setLogLevel("ERROR")
  
  val myList =List((1, "2023-07-25", 11599, "CLOSED"),
                  (2, "2023-07-25", 256, "PENDING_PAYMENT"),
                  (3, "2023-07-25", 11599, "COMPLETE"),
                  (4, "2023-07-25", 8827, "CLOSED"))
  
  val ordersDf = spark.createDataFrame(myList).toDF("orderid", "orderdate", "customerid", "status")               
  
  val newDf = ordersDf
  .withColumn("orderdate", unix_timestamp(col("orderdate").cast(DateType)))
  .withColumn("newid", monotonically_increasing_id)
  .dropDuplicates("orderdate","customerid")
  .drop("orderid")
  .sort("orderdate")
  
  newDf.printSchema()
  newDf.show()
  
  //stopping spark session
  spark.stop()
}