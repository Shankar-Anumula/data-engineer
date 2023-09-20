package practice.week16stream2

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import org.apache.spark.sql.streaming.Trigger



object StreamToStreamOuterJoin extends App{
  
  //Creating Spark Session
  val spark = SparkSession.builder()
  .appName("Stream to Stream Join")
  .master("local[2]")
  .config("spark.streaming.stopGracefullOnShutdown","true")
  .config("spark.sql.shuffle.partitions",3)
  .getOrCreate()
  
  //Setting Log Level
  spark.sparkContext.setLogLevel("ERROR")
  
  //Defining the schema for Impressions stream
  val impressionSchema = StructType(List(
        StructField("impressionID",StringType),
        StructField("ImpressionTime",TimestampType),
        StructField("CampaignName",StringType)
      ))
      
  //Defining the schema for Click stream
  val clickSchema = StructType(List(
        StructField("clickID",StringType),
        StructField("ClickTime",TimestampType)
      ))
      
  //Reading the data from socket - Impressions
  val impressionsDf = spark.readStream
  .format("socket")
  .option("host", "localhost")
  .option("port", "12347")
  .load()
  
  //Reading the data from socket - Clicks
  val clicksDf = spark.readStream
  .format("socket")
  .option("host", "localhost")
  .option("port", "12348")
  .load()
  
  //Structure the data based on the schema defined - impressionsDf
  val value1Df = impressionsDf.select(from_json(col("value"), impressionSchema).alias("value"))
  val impressionsNewDf = value1Df.select("value.*")
   .withWatermark("ImpressionTime", "30 minute") //watermark to clean the state store
   
  //Structure the data based on the schema defined - clicksDf
  val value2Df = clicksDf.select(from_json(col("value"), clickSchema).alias("value"))
  val clicksNewDf = value2Df.select("value.*")
  .withWatermark("ClickTime", "30 minute") //watermark to clean the state store
  
  //Join condition - click will be considered only when it happens within 15min of the impression time
  //val joinExpr = impressionsNewDf.col("impressionID") === clicksNewDf.col("clickID")
  val joinExpr = expr("impressionID == clickID AND ClickTime BETWEEN ImpressionTime AND ImpressionTime + interval 15 minute")
  
  //Join Type
  val joinType = "leftOuter"
  
  //Joining both the streaming data frames
  val joinedDf = impressionsNewDf.join(clicksNewDf,joinExpr,joinType)
  .drop(clicksNewDf.col("clickID"))
  
  //output to the sink
  val campaignQuery = joinedDf.writeStream
  .format("console")
  .outputMode("append")
  .option("checkpointLocation", "checkpoint-location3")
  .trigger(Trigger.ProcessingTime("15 second"))
  .start()
  
  campaignQuery.awaitTermination()
  
  
}