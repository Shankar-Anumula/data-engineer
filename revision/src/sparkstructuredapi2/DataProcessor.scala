package sparkstructuredapi2

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

case class Orders(order_id:Integer,customer_id:Integer,order_status:String)

object DataProcessor extends App{
  
  val sparkConf = new SparkConf().setMaster("local[2]").setAppName("Data Sink Table")
  val spark = SparkSession.builder()
  .config(sparkConf)
  .enableHiveSupport() //Enable HIVE Support
  .getOrCreate()
  spark.sparkContext.setLogLevel("ERROR")
  
  //regular expression to decypher - https://regex101.com/r/oHDuSe/1
  val myregex = """^(\S+) (\S+)\t(\S+),(\S+)""".r
    
  //match case with regex and case class
  def parser(line:String) = {
    line match{
      case myregex(order_id,date,customer_id,order_status) => 
        Orders(order_id.toInt,customer_id.toInt,order_status.toString)
    }
  }
    
  val orders_rawdata = spark.sparkContext.textFile("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/orders_new.csv")
  
  import spark.implicits._
  val ordersDS = orders_rawdata.map(parser).toDS().cache()
  ordersDS.printSchema()
  
  //Display all order_ids
  ordersDS.select("order_id").show()
  
  //group it based on order status
  ordersDS.groupBy("order_status").count().show()  
  
  scala.io.StdIn.readLine()
  spark.stop()
}