package assignments.week9spark

import org.apache.spark.SparkContext
import scala.math.min
import org.apache.spark.rdd.RDD.rddToPairRDDFunctions

object MinimumTemperature extends App{
  
  def parseLine(line:String)={
    
    val fields = line.split(",")
    
    val stationId = fields(0)
    val entryType = fields(2)
    val temperature = fields(3)
    
    (stationId,entryType,temperature)
    
  }
  
  
  val sc = new SparkContext("local[*]","connections")
  val input = sc.textFile("C:/All_WorkSpace/Data Engineering/Trendy Tech/Week9_Apache Spark Introduction/tempdata.csv")
  
  val parsedLines = input.map(parseLine)  
  
  val minTemps = parsedLines.filter(x => x._2 == "TMIN")
  val stationtemps = minTemps.map(x => (x._1,x._3.toFloat))
  
  val minTempsByStation = stationtemps.reduceByKey((x,y)=> min(x,y))
  
  minTempsByStation.collect.foreach(println)
  
  
}