package practice.week11

import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf
import org.apache.log4j.Level
import org.apache.log4j.Logger

object DataFramesExample extends App{
  
  /* Spark Session encapsulates the SQL/Hive/SparkContext
    val spark  = SparkSession.builder()
                  .appName("MyApplication1")
                  .master("local[2]")
                  .getOrCreate()
                  
 */
  
  //A better way of configuring
  
  Logger.getLogger("org").setLevel(Level.ERROR)
  
  val sparkConf = new SparkConf()
  
  sparkConf.set("spark.app.name", "my first application")
  sparkConf.set("spark.master","local[2]")
  
  val spark  = SparkSession.builder()
                  .config(sparkConf)
                  .getOrCreate()
                            
  //processing
                  //Actions
                  //  1. Read CSV file and calculate number of partitions and partition schema
                  //  2. Infer schema - read data and make intellegent guess for inferring schema
                  
  val ordersDf = spark.read
  .option("header", true)
  .option("inferSchema",true)
  .csv("C:/All_WorkSpace/Data Engineering/Trendy Tech/Week11_Spark Structured API - Part1/orders-201019-002101.csv")
  
  //All the below items are transformations
  // code is converted into lowlevel - shows on Thread Main in debug mode
  val groupedOrdersDf = ordersDf
  .repartition(4)
  .where("order_customer_id > 4000")
  .select("order_id","order_customer_id")
  .groupBy("order_customer_id")
  .count()
  
  //low level code inside foreach - Anonymous function - shows on Daemon Thread in Debug Mode
  groupedOrdersDf.foreach(x => {
    println(x)
  }
  )
  //Actions
                  //  3. Show the data
  ordersDf.show()
  //ordersDf.printSchema()
 
  //Emitting Log Message
  Logger.getLogger(getClass.getName).info("My Application completed successfully")
  
  scala.io.StdIn.readLine()
  
  spark.stop()
  
  
}