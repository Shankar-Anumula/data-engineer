package sparkstructuredapi2

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.DateType

object DataFramePreDefFunc extends App{
  //Creating Spark Session
  val sparkConf = new SparkConf().setAppName("UDF Column Object Demo").setMaster("local[2]")
  val spark = SparkSession.builder().config(sparkConf).getOrCreate()
  spark.sparkContext.setLogLevel("ERROR")
  
  //DEMO = createDataFrame, toDf, withColumn, unix_timestamp, monotonically_increasing_id, dropDuplicates, drop, sort
  
  val myList =List((1, "2023-07-25", 11599, "CLOSED"),
                  (2, "2023-08-25", 256, "PENDING_PAYMENT"),
                  (3, "2023-07-25", 11599, "COMPLETE"),
                  (4, "2023-09-25", 8827, "CLOSED"))
                  
  //1. Create a Dataframe from a List = createDataFrame
  val ordersDf = spark.createDataFrame(myList).toDF("order_id","order_date","order_customer_id","order_status")
  
  //2. Modify column with a Predef Function for unix/epoch time =  org.apache.spark.sql.functions.unix_timestamp
  val newDf1 = ordersDf.withColumn("order_date", unix_timestamp(col("order_date")))
  //val newDf1 = ordersDf.withColumn("order_date", unix_timestamp(col("order_date"), "yyyy-MM-dd"))

  //3. Add new column new_id which has unique ids = org.apache.spark.sql.functions.monotonically_increasing_id
  val newDf2 = ordersDf.withColumn("new_id", monotonically_increasing_id())
  
  //4. Drop duplicates using two columns - order_date and order_customer_id = .dropDuplicates(Cols*)
  val newDf3 = ordersDf.dropDuplicates("order_date", "order_customer_id")
  
  //5. Drop a column = .drop(col*)
  val newDf4 = ordersDf.drop("order_id")
  
  //6. Sort the dataframe by a column = .sort(col*)
  val newDf5 = ordersDf.sort("order_date")
  
  //consolidating all above steps
  val newOrdersDf = ordersDf.withColumn("order_date", unix_timestamp(col("order_date").cast(DateType)))
          .withColumn("new_id", monotonically_increasing_id())
          .dropDuplicates("order_date", "order_customer_id")
          .drop("order_id")
          .sort("order_date")
          
   ordersDf.printSchema()
   newOrdersDf.printSchema()
   
   ordersDf.show()
   newOrdersDf.show()
          
  //Close the Spark Session
  scala.io.StdIn.readLine()
  spark.stop()
}