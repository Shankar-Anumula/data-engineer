package practice.week12spark

import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.SaveMode

object dataWriteFileFormats extends App{
  
  Logger.getLogger("org").setLevel(Level.ERROR)
  
  val sparkConf = new SparkConf()
  .setAppName("Spark write Demo")
  .setMaster("local[2]")
  
  val spark = SparkSession.builder()
  .config(sparkConf)
  .getOrCreate()
  
  val ordersDf = spark.read
  .format("csv")
  .option("path", "C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/orders.csv")
  .option("header",true)
  .option("inferSchema",true)
  .load()
  
  /* Various file formats

  //CSV
  ordersDf.write
  .format("csv")
  .mode(SaveMode.Overwrite)
  .option("path", "C:/All_WorkSpace/Data Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/output")
  .save()
  
  
    //JSON
    ordersDf.write
    .format("json")
    .mode(SaveMode.Overwrite)
    .option("path", "C:/All_WorkSpace/Data Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/output")
    .save()
    
    //PARQUET - default format, need not specify the format option
    ordersDf.write
    //.format("parquet")
    .mode(SaveMode.Overwrite)
    .option("path", "C:/All_WorkSpace/Data Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/output")
    .save()
  * */
  
  //Partitioning the data
  
  print("ordersdf has "+ordersDf.rdd.getNumPartitions + " partitions")
  
  val ordersDfRep = ordersDf.repartition(4)
  
  print("ordersdf has "+ordersDfRep.rdd.getNumPartitions + " partitions")
  
  //import spark-avro jar - AVRO is not supported by default
  ordersDfRep.write
  .format("avro")
  .mode(SaveMode.Overwrite)
  .option("path", "C:/All_WorkSpace/Data Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/output1")
  .save()
  
    spark.stop()
  
}