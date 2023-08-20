package practice.week16stream2

import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.Trigger

object StreamingWordCount extends App{
  
  val sparkConf = new SparkConf()
  .setAppName("My Streaming Application")
  .setMaster("local[2]")
  
  val spark = SparkSession.builder()
  .config(sparkConf)
  .config("spark.sql.shuffle.partitions", 3)
  .config("spark.streaming.stopGracefullyOnShutdown","true")
  .getOrCreate()
  
  spark.sparkContext.setLogLevel("ERROR")
  
  //1. Read from the stream
  val linesDf = spark.readStream
  .format("socket")
  .option("host", "localhost")
  .option("port", "12345")
  .load()
  
  linesDf.printSchema()
  
  //2. process - find the frequency of each word
  val wordsDf = linesDf.selectExpr("explode(split(value,' '))as word")
  val countsDf = wordsDf.groupBy("word").count()
  
  //3. write to sink
  val wordCountQuery = countsDf.writeStream
  .format("console")
  .outputMode("complete")
  .option("checkpointLocation","checkpoint-location101")
  .trigger(Trigger.ProcessingTime("30 seconds"))
  .start()
  
  wordCountQuery.awaitTermination()
  
  
  
  //spark.close()

  
  
}