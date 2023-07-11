import org.apache.spark.SparkContext


object RatingsCalculator extends App {
  val sc = new SparkContext("local[*]","movieratings")
  
  val movieratings = sc.textFile("C:/All_WorkSpace/Data Engineering/Trendy Tech/Week9_Apache Spark Introduction/moviedata-201008-180523.data")
  .map(x => x.split("\t")(2))
  .map(x => (x,1))
  .reduceByKey((x,y)=> x+y)
  .sortBy(x => x._1,false)
  .collect
  
  movieratings.foreach(println)
                     
  //countByValue is an action just like collect, it counts the value
  //it is a variable and not RDD
  //Operation after countByValue doesnt achieve parallelism so has to be the last operation
  val movieratings2 = sc.textFile("C:/All_WorkSpace/Data Engineering/Trendy Tech/Week9_Apache Spark Introduction/moviedata-201008-180523.data")
  .map(x => x.split("\t")(2))
  .countByValue
  
  movieratings2.foreach(println)
  
  scala.io.StdIn.readLine()
  
}