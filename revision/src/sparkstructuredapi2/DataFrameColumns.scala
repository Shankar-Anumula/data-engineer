package sparkstructuredapi2

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object DataFrameColumns extends App{
  val sparkConf = new SparkConf().setAppName("DataFrame Reader").setMaster("local[2]")
  val spark = SparkSession.builder().config(sparkConf).getOrCreate()
  spark.sparkContext.setLogLevel("ERROR")
  
  /*Datasets
    CSV - orders.csv
   */
  
  //CSV - format, header and inferSchema provided
  val ordersDf = spark.read
                      .format("csv")
                      .option("header", true)
                      .option("inferSchema",true)
                      .option("path","C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/orders.csv")
                      .load
                      
  ordersDf.printSchema()
  
 /*
  * 1. Column String - columns are specified in string format
  * 2. Column Object 
  * 		a) uses column and col methods
  * 		b) scala specific syntax $ and '
  * 3. Column Expression using selectExpr
  
  
  * Note: In a Single Select, We cannot mix
		○ Column String and Column Object
		○ Column String and Column Expression
		○ Column Object and Column Expression
  
  */

  //1 - COlumn String - columns are specified in string format
  //scala specific syntax $ and '
  ordersDf.select("order_id", "order_status").show()
  
  //2. Column Object - uses column and col methods
  ordersDf.select(column("order_id"), col("order_status")).show()
  
  //2. Column Object - scala specific syntax $ and '
  import spark.implicits._
  ordersDf.select($"order_id", 'order_status).show()
  
  //3. Column expression using expr
  ordersDf.select(column("order_id"),col("order_date"),expr("concat(order_status,'_STATUS')")).show(false)
  
  //3. Column Expression using selectExpr
  ordersDf.selectExpr("order_id","order_date","concat(order_status,'_STATUS')").show(false)
  
  //use a combination of all object types
  ordersDf.select(column("order_id"),col("order_date"),$"order_customer_id",'order_status).show()
  
  /*
   * 
   * 
   */
  
  scala.io.StdIn.readLine()
  spark.stop()
}