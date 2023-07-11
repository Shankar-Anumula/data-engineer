package practice.week11

import org.apache.spark.SparkContext

object topMovies extends App{
  
  val sc = new SparkContext("local[*]","topmovies")
  
  val ratingsRdd = sc.textFile("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week11_Spark Structured API - Part1/ratings-201019-002101.dat")
  
  //Fetch movieid and rating
  val mappedRdd = ratingsRdd.map( x=> {
     val fields = x.split("::") 
    (fields(1),fields(2))
  })
  
  //input
  //(1193,5)
  //1193,4
  //1193,4
  
  //output
  //1193,(5.0,1.0)
  //1193,(3.0,1.0)
  //1193,(4.0, 1.0)
  
  val newMappedRdd = mappedRdd.mapValues(x => (x.toFloat,1.0))
  
  //Input
  //1193,(5.0,1.0)
  //1193,(3.0,1.0)
  //1193,(4.0, 1.0)
  
  //output
  //1193,(12.0,3.0)
  
  val reducedRdd = newMappedRdd.reduceByKey((x,y) => (x._1 + y._1, x._2+y._2))
  
  //CRITERIA 1 : Now filter movies that are rated atleast 1000 times
  
  //Input
  //(1193,(12.0,3.0))
  
  val filteredRdd = reducedRdd.filter(x => x._2._2 > 1000)
  
  //calculate average rating
  
  //Input
  //(1193,(12000.0,3000.0))
  
  //output
  //(1193,4)
  
   //one single row input and 1 single row output - so using Map
  
  val filteredRdd2 = filteredRdd.mapValues(x => x._1/x._2)
  
  //CRITERIA 2 : filter movies with rating greater than 4.5
  
  val ratingsProcessedRdd = filteredRdd2.filter(x => (x._2 > 4.5))
  
  //ratingsProcessedRdd.foreach(println)
  
  
  val moviesRdd = sc.textFile("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week11_Spark Structured API - Part1/movies-201019-002101.dat")
  
  //Fetch movieid and moviename
  val moviesMappedRdd = moviesRdd.map( x=> {
     val fields = x.split("::") 
    (fields(0),fields(1))
  })
  
  //Join the RDDs to fetch the movies based on ratings
  
  val joinedRdd = moviesMappedRdd.join(ratingsProcessedRdd)
  
  val topMovies = joinedRdd.map(x => x._2._1)
  
  topMovies.foreach(println)
  
  scala.io.StdIn.readLine()
}