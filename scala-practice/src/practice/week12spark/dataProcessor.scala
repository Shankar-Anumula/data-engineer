package practice.week12spark

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.log4j.Level
import org.apache.log4j.Logger

object dataProcessor extends App{
  Logger.getLogger("org").setLevel(Level.ERROR)
  
  
  //regular expression to decypher - https://regex101.com/r/oHDuSe/1
  //1 2013-07-25	11599,CLOSED
  
  val myregex = """^(\S+) (\S+)\t(\S+),(\S+)""".r
  
  //associating structure to RDD
  case class Orders(order_id:Integer,customer_id:Integer,order_status:String)
  
  def parser(line:String) = {
    line match{
      case myregex(order_id,date,customer_id,order_status) => 
        Orders(order_id.toInt,customer_id.toInt,order_status.toString)
    }
  }
  
  val sparkConf = new SparkConf()
  .setAppName("Data Table")
  .setMaster("local[2]")
  
  val spark = SparkSession.builder()
  .config(sparkConf)
  .getOrCreate()
  
  //Set loggin level at spark
  spark.sparkContext.setLogLevel("ERROR")
  
  val lines = spark.sparkContext.textFile("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/orders_new.csv")
  
  import spark.implicits._
  
  //converting to Dataset early in the processing
  //caching so we dont have to redo it for every action
  val ordersDS = lines.map(parser).toDS().cache()
  
  ordersDS.printSchema()
  
  //show all order_ids
  ordersDS.select("order_id").show()
  
  //group it based on order status
  ordersDS.groupBy("order_status").count().show()  
  
  spark.stop() 
  
}