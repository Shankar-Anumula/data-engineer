package assignments.week16stream2

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.Trigger
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._

object WeatherStream2 extends App{
  
  //Creating Spark Session
  val spark = SparkSession.builder()
  .appName("Assignment problem2")
  .master("local[2]")
  .config("spark.streaming.stopGracefullOnShutdown","true")
  .config("spark.sql.shuffle.partitions",3)
  .getOrCreate()
  
  //Setting Log Level
  spark.sparkContext.setLogLevel("ERROR")
  
    val weatherSchema = StructType(List(
        StructField("DateTime",TimestampType),
        StructField("Temperature",DoubleType),
        StructField("Humidity",DoubleType),
        StructField("WindSpeed",DoubleType),
        StructField("Pressure",DoubleType),
        StructField("Summary",StringType)
      ))
  
  //read input file stream
  val weatherSocketDF = spark.readStream
  .format("socket")
  .option("host", "localhost")
  .option("port", "1234")
  .load()
  
  weatherSocketDF.printSchema()
  
  val valueDF = weatherSocketDF.select(from_json(col("value"),weatherSchema).alias("Value"))
  val refinedWeatherDF = valueDF.select("Value.*")   
  
  refinedWeatherDF.printSchema()
  
  val windowAggWeatherDF = refinedWeatherDF
  .withWatermark("DateTime", "30 minute")
  .groupBy(window(col("DateTime"), "15 minute"))
  .agg(avg("Temperature").alias("totalTemperature"))
  
  windowAggWeatherDF.printSchema()
  
  val weatherOutputDf = windowAggWeatherDF.select("window.start", "window.end","totalTemperature")
  
  weatherOutputDf.printSchema()
  
  //write to Sink
  val weatherOutputQuery = weatherOutputDf.writeStream
  .format("console") //.format("csv")
  .outputMode("update")
  .option("checkpointLocation", "checkpoint-location2")
  .trigger(Trigger.ProcessingTime("15 seconds"))
  .start()
  
  weatherOutputQuery.awaitTermination()
  
}