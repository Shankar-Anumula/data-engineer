package practice.week13opt1

import org.apache.spark.SparkContext

object bigLogCluster extends App{
  
  
    val sc = new SparkContext("local[*]", "bigdatacampaign")
  
    val myList = List("WARN: 2016-12-31 04:19:32",
                    "FATAL: 2016-12-31 03:22:34",
                    "WARN: 2016-12-31 03:21:21",
                    "INFO: 2015-12-31 14:32:21",
                    "FATAL: 2015-12-31 14:23:20")
                    
    val rdd1 = sc.parallelize(myList)
    
    val rdd2  =rdd1.map(x => (x.split(":")(0),x.split(":")(1)))
    val rdd3 = rdd2.groupByKey
    val rdd4 = rdd3.map(x => (x._1,x._2.size))
    rdd4.collect().foreach(println)
    
    
  
  
}