package sparkstructuredapi2

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.SaveMode

object DataframeWriter extends App{
 //Dataset - C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2 
  
  val sparkConf = new SparkConf().setAppName("Dataframe Writer").setMaster("local[2]")
  val spark = SparkSession.builder().config(sparkConf).getOrCreate()
  spark.sparkContext.setLogLevel("ERROR")
  
  val ordersDf = spark.read.format("csv")
  .option("header", true)
  .option("inferSchema", true)  .option("path", "C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/orders.csv")
  .load
  
  ordersDf.printSchema()
  
  //Different file formats
  ordersDf.write.format("csv")
  .option("header", true)
  .mode(SaveMode.Append)
  .option("path", "C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/Output/csv")
  .save
  
  ordersDf.write.format("json")
  .mode(SaveMode.Append)
  .option("path", "C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/Output/json")
  .save
  
  //PARQUET - default format, need not specify the format option
  ordersDf.write
  //.format("parquet")
  .mode(SaveMode.Append)
  .option("path", "C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/Output/parquet")
  .save
  
  //Partitioning the data
  
  print("ordersdf has "+ordersDf.rdd.getNumPartitions + " partitions")

  val ordersDfRep = ordersDf.repartition(4)
  
  print("ordersdf has "+ordersDfRep.rdd.getNumPartitions + " partitions")
  
  //import spark-avro jar - AVRO is not supported by default
  ordersDf.write.format("avro")
  .mode(SaveMode.Append)
  .option("path", "C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/Output/avro")
  .save
  
  
  //Partitioning - partitionBy column
  ordersDf.write.format("csv")
  .option("header", true)
  .mode(SaveMode.Overwrite)
  .partitionBy("order_status")
  .option("path", "C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/Output/csv-partitions")
  .save
  
  ordersDf.write.format("csv")
  .option("header", true)
  .mode(SaveMode.Overwrite)
  .option("maxRecordsPerFile", 2500)
  .option("path", "C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/Output/csv-maxrecords")
  .save
  
  ordersDf.write.format("csv")
  .option("header", true)
  .mode(SaveMode.Overwrite)
  .partitionBy("order_status")
  .option("maxRecordsPerFile", 2500)
  .option("path", "C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/Output/csv-partitions-maxrecords")
  .save
 
  //Bucketing - BucketBy works with SaveAsTable() only
  ordersDf.write.format("csv")
  .option("header", true)
  .mode(SaveMode.Overwrite)
  .bucketBy(5, "order_id")
  .option("path", "C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/Output/csv-buckets-maxrecords")
  .saveAsTable("BucketsDemo")
  
  scala.io.StdIn.readLine()
  spark.stop()
  
}