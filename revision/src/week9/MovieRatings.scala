package week9

import org.apache.spark.SparkContext

object MovieRatings extends App{
  
  /*
   * How many times movies were rated 1-5 stars
   
   	User_id movie_id rating_given timestamp
   	just use rating_given column for the processing
   	
   	using CountByValue
   
   * 
   */
  
    val sc = new SparkContext("local[*]","movieratings")
    val movieratings = sc.textFile("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week9_Apache Spark Introduction/moviedata-201008-180523.data")
            .map(x => x.split("\t")(2))
            .map(x => (x,1))
            .reduceByKey((x,y) => x+y)
            .sortBy(x => x._1,false)
            .collect
            
    
    //using countByValue
    val movieratings2 = sc.textFile("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week9_Apache Spark Introduction/moviedata-201008-180523.data")
            .map(x => x.split("\t")(2))
            .countByValue
            
            
    movieratings.foreach(println)   
    movieratings2.foreach(println)
    
    scala.io.StdIn.readLine()
}