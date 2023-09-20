package practice.week16stream2

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming.Trigger

object StreamingJoinStaticLeftOuter extends App{
  
  val spark = SparkSession.builder()
      .master("local[2]")
      .appName("Streaming Join Static")
      .config("spark.streaming.stopGracefullyOnShutdown","true")
      .config("spark.sql.shuffle.partitions",3)
      .getOrCreate()
      
  spark.sparkContext.setLogLevel("ERROR")
  
  val transactionSchema = 
    StructType(List(
        StructField("card_id",LongType),
        StructField("amount",IntegerType),
        StructField("postcode",IntegerType),
        StructField("pos_id",LongType),
        StructField("transaction_dt",TimestampType)
        ))
        
   val transactionDf = spark.readStream
   .format("socket")
   .option("host", "localhost")
   .option("port","12346")
   .load()
   
   val valueDf = transactionDf.select(from_json(col("value"),transactionSchema).alias("value"))
   val refinedTransactionDf = valueDf.select("value.*")    
   
   //transactionDf.printSchema()
   //valueDf.printSchema()
   //refinedTransactionDf.printSchema()
   
   val memberDf = spark.read
   .format("csv")
   .option("path", "C:/All_WorkSpace/Data-Engineering/Trendy Tech/Weeks16_Spark Streaming 2/member.csv")
   .option("header","true")
   .option("inferSchema", "true")
   .load()
   
   val joinExpr = refinedTransactionDf.col("card_id") === memberDf.col("card_id")
   val joinType = "leftouter"
   //val joinType = "rightouter" //giving right outer when streaming dataframe on left will give Analysis exception
   
   val enrichedDf = refinedTransactionDf.join(memberDf,joinExpr,joinType)
   .drop(memberDf.col("card_id"))
   
   val transactionQuery = enrichedDf.writeStream
   .format("console")
   .outputMode("update")
   .option("checkpointLocation","checkpoint2")
   .trigger(Trigger.ProcessingTime("15 seconds"))
   .start()
   
   transactionQuery.awaitTermination()
  
}