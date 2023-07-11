package practice.week10

import org.apache.spark.SparkContext

object reduceDemo extends App{
  
  val sc  = new SparkContext("local[*]","loglevel")
  
  val a = 1 to 100
  
  val base = sc.parallelize(a)
  
  val output = base.reduce((x,y) => (x+y))
  
  println(output)
  
    scala.io.StdIn.readLine()

  
}