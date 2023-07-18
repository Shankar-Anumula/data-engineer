package practice.week12spark

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object BigLogTable extends App{
  
  val sparkConf = new SparkConf()
  .setAppName("Log Level")
  .setMaster("local[2]")
  
  val spark  = SparkSession.builder()
  .config(sparkConf)
  .getOrCreate()
  
  spark.sparkContext.setLogLevel("ERROR")
  
  //Reading Big log file and loading a dataframe
  val bigLogDF = spark.read
  .format("csv")
  .option("header", true)
  .option("inferSchema", true)
  .option("path","C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/biglog.txt")
  .load()
  
  bigLogDF.createOrReplaceTempView("big_log_table")
  
  //In below query - month is alphabetic ordered
   spark.sql("""
                select level, date_format(datetime,'MMMM') as month, count(1) as total 
                from big_log_table
                group by level, month
                order by month, level
             """)
             .show()
   
   //Order by the month correctly as per the calendar
   spark.sql("""
                select level, date_format(datetime,'MMMM') as month,
                cast(first(date_format(datetime,'M')) as int) as monthnum, count(1) as total 
                from big_log_table
                group by level, month
                order by monthnum, level
             """)
             .drop("monthnum") 
             .show(60)
   
   //Using pivot() function to make the results more meaningful - transposing row to column
   spark.sql("""
                select level, date_format(datetime,'MMMM') as month,
                cast(date_format(datetime,'M') as int) as monthnum 
                from big_log_table
            """)
            .groupBy("level")
            .pivot("monthnum")
            .count()
            .show(60)
            
   //	When we say pivot(monthnum), system has to run a query to find distinct months and showcase in the column
	 // Since the month is static - we don’t need system to calculate and create a list of months to hardcode
	 // So the system will know the hardcode list to put as columns and saves systems time
                        
   val columns = List("January","February","March","April","May","June","July","August","September","October","November","December")
   val results = spark.sql("""
                               select level, date_format(datetime,'MMMM') as month
                               from big_log_table
                           """)
                           .groupBy("level")
                           .pivot("month",columns)
                           .count()
                           .show(60)
  
  spark.stop()
    
}