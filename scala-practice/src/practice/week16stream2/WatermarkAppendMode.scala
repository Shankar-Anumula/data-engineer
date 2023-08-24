package practice.week16stream2

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming.Trigger

object WatermarkAppendMode extends App{
  
  val sparkConf = new SparkConf()
  .setAppName("My Streaming from File")
  .setMaster("local[2]")
  
  val spark = SparkSession.builder()
  .config(sparkConf)
  .config("spark.sql.shuffle.partitions",3)
  .config("spark.streaming.stopGracefullyOnShutdown","true")
  .getOrCreate()
  
  spark.sparkContext.setLogLevel("ERROR")
  
  val ordersSchema = StructType(List(
      StructField("order_id",IntegerType),
      //StructField("order_date",StringType),
      StructField("order_date",TimestampType),
      StructField("order_customer_id",IntegerType),
      StructField("order_status",StringType),
      StructField("amount",IntegerType)
    ))
  

  //read the data from socket
  val ordersDf = spark.readStream
  .format("socket")
  .option("host","localhost")
  .option("port","12349")
  .load()
  
  //process
  val valueDf = ordersDf.select(from_json(col("value"),ordersSchema).alias("value"))
      
  valueDf.printSchema()
  val refinedOrdersDf = valueDf.select("value.*")
  
  val windowAggDf = refinedOrdersDf
  .withWatermark("order_date", "30 minute") //setting watermark
  .groupBy(window(col("order_date"),"15 minute"))
  .agg(sum("amount").alias("totalInvoice"))
  
  val outputDf = windowAggDf.select("window.start", "window.end","totalInvoice")
  outputDf.printSchema()
  
  //write to Sink
  val ordersQuery = outputDf.writeStream
  .format("console")
  //.outputMode("update")
  .outputMode("append")
  .option("checkpointLocation", "checkpoint-location2")
  .trigger(Trigger.ProcessingTime("15 second"))
  .start()
  
  ordersQuery.awaitTermination()
  
}