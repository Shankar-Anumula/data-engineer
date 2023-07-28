package practice.week14opt2

import org.apache.spark.SparkContext

object LogLevelGrouping {
  
  /** Our main function where the action happens */
  
  def main(args:Array[String]){
  
  val sc = new SparkContext()
                    
  val base_rdd = sc.textFile(args(0))
  
  base_rdd.map(x => {
   (x.split(":")(0),x.split(":")(1))  }).groupByKey.map(x => (x._1, x._2.size)).collect().foreach(println)
   
  }
}