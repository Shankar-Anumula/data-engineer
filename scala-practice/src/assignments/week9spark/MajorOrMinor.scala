package assignments.week9spark

import org.apache.spark.SparkContext

object MajorOrMinor extends App{
  
  val sc = new SparkContext("local[*]","connections")
  
  val input = sc.textFile("C:/All_WorkSpace/Data Engineering/Trendy Tech/Week9_Apache Spark Introduction/dataset1")
  val rdd1 = input.map(x => {
    var fields = x.split(",")
    if(fields(1).toInt > 18)
       (fields(0),fields(1),fields(2),"Y")
     else
       (fields(0),fields(1),fields(2),"N")
  })
  
  rdd1.collect.foreach(println)
  
  
}