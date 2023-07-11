package practice.week10

import org.apache.spark.SparkContext

object accumulatorDemo extends App{
  
  val sc = new SparkContext("local[*]","sparkaccumulator")
  
  val myrdd = sc.textFile("C:/All_WorkSpace/Data Engineering/Trendy Tech/Week10_Spark Indepth/samplefile-201014-183159.txt")
  
  val myaccum = sc.longAccumulator("blank lines accumulaor")
  
  myrdd.foreach(x => if (x=="") myaccum.add(1))
    
  println(myaccum.value)
  
}
