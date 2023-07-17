package practice.week12spark

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
//import org.apache.spark.sql.functions._

object LogLevelDemo{
  
  //attaching schema to dataset using case class
  case class Logging(level:String,datetime:String)
  
  def mapper(line:String): Logging = {
    val fields = line.split(',')
    
    val logging:Logging = Logging(fields(0),fields(1))
    return logging
  
  }
  
  /** Our main function where the action happens */
  
  def main(args:Array[String]){
  
  val sparkConf = new SparkConf()
  .setAppName("Log Level")
  .setMaster("local[2]")
  
  val spark  = SparkSession.builder()
  .config(sparkConf)
  .getOrCreate()
  
  spark.sparkContext.setLogLevel("ERROR")
  
  import spark.implicits._
  
  val myList = List("WARN, 2016-12-31 04:19:32",
                    "FATAL, 2016-12-31 03:22:34",
                    "WARN, 2016-12-31 03:21:21",
                    "INFO, 2015-12-31 14:32:21",
                    "FATAL, 2015-12-31 14:23:20")
                    
  val rdd1 = spark.sparkContext.parallelize(myList)
  val rdd2 = rdd1.map(mapper)
  val df1 = rdd2.toDF()
  
  //df1.show()
  
  df1.createOrReplaceTempView("logging_table")
  spark.sql("Select level,collect_list(datetime) from logging_table group by level order by level").show(false)
  spark.sql("Select level,count(datetime) from logging_table group by level order by level").show(false)
  
  //Approach1
  spark.sql("Select level,date_format(datetime,'MMMM') as month,count(*) as record_count from logging_table group by level,month order by Level").show(false)
  
  //Approach2 using a new temp table
  val df2 =  spark.sql("Select level,date_format(datetime,'MMMM') as month from logging_table")
  df2.createOrReplaceTempView("new_logging_table")
  spark.sql("Select level,month,count(*) as record_count from new_logging_table group by level,month order by level").show(false)
            
  spark.stop()
  }
  
}