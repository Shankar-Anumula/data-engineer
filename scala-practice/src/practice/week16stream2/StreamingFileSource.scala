package practice.week16stream2

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.Trigger

object StreamingFileSource extends App{
  
  val sparkConf = new SparkConf()
  .setAppName("My Streaming from File")
  .setMaster("local[2]")
  
  val spark = SparkSession.builder()
  .config(sparkConf)
  .config("spark.sql.shuffle.partitions",3)
  .config("spark.streaming.stopGracefullyOnShutdown","true")
  .config("spark.sql.streaming.schemaInference","true")
  .getOrCreate()
  
  spark.sparkContext.setLogLevel("ERROR")
  
  //read file source
  
  val ordersDf = spark.readStream
  .format("json")
  .option("path", "myinputfolder")
  .option("cleanSource","archive")
  .option("sourceArchiveDir","myarchivefolder")
  .option("maxFilesPerTrigger", 1)
  .load()

  //process
  ordersDf.createOrReplaceTempView("orders")
  val completedOrders = spark.sql("select * from orders where order_status = 'COMPLETE'")
  
  //write to sink
  val ordersQuery = completedOrders.writeStream
  .format("json")
  .outputMode("append")
  .option("path", "myoutputfolder")
  .option("checkpointLocation","checkpoint-location101")
  .trigger(Trigger.ProcessingTime("30 seconds"))
  .start()
  
  ordersQuery.awaitTermination()
 
  
}