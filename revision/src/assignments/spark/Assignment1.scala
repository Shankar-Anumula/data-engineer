package assignments.spark

import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.SparkContext
import scala.math.min



object Assignment1 extends App{
  
  Logger.getLogger("org").setLevel(Level.ERROR)
  val sc  = new SparkContext("local[*]", "assignment1")
  
  /* QUESTION 1
   * Given an Input data set with name, age and city - //sumit,30,bangalore
   * if age > 18 add a new column that’s populated with ‘Y’ else ‘N’
   */
  
  val data1 = sc.textFile("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week9_Apache Spark Introduction/Assignment/dataset1")
  
  val results = data1.map(x => {
    val fields = x.split(",")
    if(fields(1).toInt>18)
      (fields(0),fields(1),fields(2),"Y")
    else
      (fields(0),fields(1),fields(2),"N")
  }).collect
  
  results.foreach(println)
  
   /* QUESTION 2
   * Given the input file where columns are stationId, timeOfTheReading, readingType,temperatureRecorded, and few other columns...
   * ITE00100554,18000101,TMAX,-75,,,E,
   * ITE00100554,18000101,TMIN,-148,,,E,
   * We need to find the minimum temperature for each station id.
   */
  
  val data2 = sc.textFile("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week9_Apache Spark Introduction/Assignment/tempdata.csv")
  val results2 = data2.map(x => {
                                  val fields = x.split(",")
                                  (fields(0), fields(2), fields(3))
                                })
                      .filter(x => x._2 == "TMIN")
                      .map(x => (x._1,x._3.toInt))
                      .reduceByKey((x,y) => min(x,y))
                      .collect

  results2.foreach(println)
  
  scala.io.StdIn.readLine()
}