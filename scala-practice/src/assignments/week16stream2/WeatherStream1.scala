package assignments.week16stream2

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.Trigger
import org.apache.spark.sql.types._

object WeatherStream1 extends App{
  
  //Creating Spark Session
  val spark = SparkSession.builder()
  .appName("Stream to Stream Join")
  .master("local[2]")
  .config("spark.streaming.stopGracefullOnShutdown","true")
  .config("spark.sql.shuffle.partitions",3)
  .getOrCreate()
  
  //Setting Log Level
  spark.sparkContext.setLogLevel("ERROR")
  
    val weatherSchema = StructType(List(
        StructField("DateTime",TimestampType),
        StructField("Temperature",DoubleType),
        StructField("Humidity",FloatType),
        StructField("WindSpeed",DoubleType),
        StructField("Pressure",DoubleType),
        StructField("Summary",StringType)
      ))
  
  //read input file stream
  val weatherHistoryDF = spark.readStream
  .format("csv")
  .schema(weatherSchema)
  .option("path", "myinputcsv")
  .option("maxFilesPerTrigger", 1)
  .load()
  
  weatherHistoryDF.printSchema()
   
  //process data
  weatherHistoryDF.createOrReplaceTempView("weatherHistory")
  val weatherReport = spark.sql("select * from weatherHistory where WindSpeed < 11.0000 and Summary = 'Partly Cloudy'")
  
  weatherReport.printSchema()
  
  //write to sink
  val weatherQuery1 = weatherReport.writeStream
  .format("json") //.format("csv")
  .outputMode("append")
  .option("path", "myoutput1")
  .option("checkpointLocation","checkpoint-location1")
  .trigger(Trigger.ProcessingTime("30 seconds"))
  .start()
  
  weatherQuery1.awaitTermination()
  
}