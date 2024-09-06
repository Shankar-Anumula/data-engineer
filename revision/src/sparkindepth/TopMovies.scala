package sparkindepth

import org.apache.spark.SparkContext

object TopMovies extends App{
  
  val sc = new SparkContext("local[*]", "Top Movies")
  sc.setLogLevel("ERROR")
  
  /*Datasets
   			C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week11_Spark Structured API - Part1/movies-201019-002101.dat
   			C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week11_Spark Structured API - Part1/ratings-201019-002101.dat
   			
   			Ratings - SlNo, movie_id, movie_rating,userid
   			Movies - movie_id, movie_name
   
    	1. Movies that are atleast rated by 1000 people
    	2. Movies that have ratings greater than 4.5
   */
  
  val moviesRDD = sc.textFile("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week11_Spark Structured API - Part1/movies-201019-002101.dat")
                    .map(x => (x.split("::")(0),x.split("::")(1)))
                    
  val ratingsProcessedRDD = sc.textFile("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week11_Spark Structured API - Part1/ratings-201019-002101.dat")
                     .map(x => (x.split("::")(1),x.split("::")(2)))
                     .map(x => (x._1,(x._2.toFloat,1.0)))
                     .reduceByKey((x,y) => (x._1+y._1,x._2+y._2))
                     
             

  
  val moviesRated1000Times = ratingsProcessedRDD.filter(x => x._2._2 > 1000)
  val moviesRatedAbove4point5 = ratingsProcessedRDD.mapValues(x => x._1/x._2)
                                                    .filter(x => (x._2 > 4.5))
                                                    
  //val MostRatedMovies = moviesRated1000Times.join(moviesRDD).map(x => x._2._2)                                                
  //val TopRatedMovies = moviesRatedAbove4point5.join(moviesRDD).map(x => x._2._2)
  
  
   // using BROADCAST JOIN                                                 
   var movies = sc.broadcast(moviesRDD.collectAsMap())                                              
   val MostRatedMovies = moviesRated1000Times.map { case (movieId, (ratingSum, count)) =>
                                                        val movieName = movies.value.getOrElse(movieId, "UNKNOWN")
                                                        (movieName)
                                                  }

  val TopRatedMovies = moviesRatedAbove4point5.map { case (movieId, avgRating) =>
                                                        val movieName = movies.value.getOrElse(movieId, "UNKNOWN")
                                                        (movieName)
                                                   }
	
                                                    
  println("\n Most Rated Movies : Movies rated atleast 1000 times ------------------------>")
  MostRatedMovies.collect.foreach(println)
  
  println("\n Top Rated Movies : Movies rated greater than 4.5 ------------------------>")
  TopRatedMovies.collect.foreach(println)
  
  scala.io.StdIn.readLine()
  sc.stop()
}