package sparkstructuredapi2

import org.apache.spark.sql.Dataset
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.Row

object DataFrameUDFSQLExpr extends App{
  
  //UDF Function for checking age
  def ageCheck(age:Int):String = {
    if(age>18) "Y" else "N"
  }
  
  val sparkConf = new SparkConf().setAppName("DataFrame Reader").setMaster("local[2]")
  val spark = SparkSession.builder().config(sparkConf).getOrCreate()
  spark.sparkContext.setLogLevel("ERROR")
  
  /*Datasets CSV - C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/dataset1
   */
  val dataDf = spark.read
                      .format("csv")
                      .option("inferSchema",true)
                      .option("path","C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week12_Apache Spark - Structured API Part-2/dataset1")
                      .load
  
  val namesDf:Dataset[Row] = dataDf.toDF("name","age","city")

  //SQL/String expression UDF
  spark.udf.register("parseAgeFunction",ageCheck(_:Int):String)
  val parseAge = udf(ageCheck(_:Int):String)
  //spark.udf.register("parseAgeFunction",(x:Int) => {if(x>18) "Y" else "N"})
  
   //SQL UDFs are registered in catalog, so we convert to view and use SQL
  spark.catalog.listFunctions().filter(x => x.name == "parseAge").show()
  spark.catalog.listFunctions().filter(x => x.name == "parseAgeFunction").show()
  
  namesDf.createOrReplaceTempView("peopleTable")
  spark.sql("select name, age, city, parseAgeFunction(age) as adult from peopleTable").show
  
  //namesDf.printSchema()
  //namesDf.show()
  
  //val personDf = namesDf.withColumn("adult", expr("parseAgeFunction(age)"))
  //personDf.show()
  
  scala.io.StdIn.readLine()
  spark.stop()
}