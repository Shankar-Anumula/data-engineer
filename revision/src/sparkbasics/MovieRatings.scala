package sparkbasics

import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.SparkContext

object MovieRatings extends App{
  
  /*
   * How many times movies were rated 1-5 stars
   
   	User_id movie_id rating_given timestamp
   	just use rating_given column for the processing
   	
   	using CountByValue
   
   * 
   */
  
  Logger.getLogger("org").setLevel(Level.ERROR)
  
  val sc = new SparkContext("local[*]","movie ratings")
  
  val input = sc.textFile("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week9_Apache Spark Introduction/moviedata-201008-180523.data")
  
  val ratings = input.map(x => (x.split("\t")(2),1))
                     .reduceByKey((x,y) => (x+y))
                     .sortByKey(false)
                     .collect
                     
  ratings.foreach(println)
                     
 //using CountByValue to acheive the above
                     //- its an Action, returns local variable not RDD
                     //- not recommended when there are more operations to perform
   
  val ratings2 = input.map(x => x.split("\t")(2))
                     .countByValue
                     
  
  ratings2.foreach(println)
  
  scala.io.StdIn.readLine()

}