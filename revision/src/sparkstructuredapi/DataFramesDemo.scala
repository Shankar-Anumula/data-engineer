package sparkstructuredapi

import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf
import org.apache.log4j.Logger
import org.apache.log4j.Level

object DataFramesDemo extends App{
  
  //Spark Session encapsulates the SQL/Hive/SparkContext
  /* val spark  = SparkSession.builder()
                  .appName("MyApplication1")
                  .master("local[2]")
                  .getOrCreate()           
 */
  Logger.getLogger("org").setLevel(Level.ERROR)
  
  //A better way of configuring - Spark Conf
  val sparkConf = new SparkConf()
  
  //Method 1
  sparkConf.set("spark.app.name", "my first application")
  sparkConf.set("spark.master","local[2]")
  
  //Method 2
  //sparkConf.setAppName("Data Frame Example")
  //sparkConf.setMaster("local[2]")
  
  //Create Spark Session using Spark Conf
  val spark = SparkSession.builder()
                          .config(sparkConf)
                          .getOrCreate()
                          
  spark.sparkContext.setLogLevel("ERROR")                        
                          
  //Processing steps here                        
     //Dataset - orders-201019-002101.csv
                          
  /*
    //val ordersDf = spark.read.csv("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week11_Spark Structured API - Part1/orders-201019-002101.csv")
    ordersDf.show()
    ordersDf.printSchema()
    
    //spark assigns default columns names as _c0, _c1....
    //Default schema will be string
  */
                          
  val ordersDf = spark.read
                          .option("header", true)
                          .option("inferSchema", true)
                          .csv("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week11_Spark Structured API - Part1/orders-201019-002101.csv")
  
    //ordersDf.show()
    //ordersDf.printSchema()
    
  /* 
   If we look at spark UI, there are multiple jobs/Actions
   
   Actions: =============================>
    1. Read is an action
    2. Infer Schema is another action
    3. Show is another action
    
    PrintSchema is not an action, it just fetches schema info from metadata
    
  */
    
    
  //All the below items are transformations
  // code is converted into lowlevel - shows on Thread Main in debug mode
  val groupedOrdersDf = ordersDf
                                .repartition(4)
                                //.coalesce(4)
                                .where("order_customer_id > 4000")
                                .select("order_id","order_customer_id")
                                .groupBy("order_customer_id")
                                .count()
    
  //groupedOrdersDf.show()
  
  //Introducing Low level code here
  groupedOrdersDf.foreach(x => {println(x)})
  
                                
  //ordersDf.printSchema()
  
  scala.io.StdIn.readLine()
  
  //always terminate spark session                        
  spark.stop()
}