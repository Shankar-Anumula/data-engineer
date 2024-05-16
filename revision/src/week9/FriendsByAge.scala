package week9

import org.apache.spark.SparkContext

object FriendsByAge extends App{
  
  /*Find average number of connections for each age
   
   Row_id, name, age, number_of_connections
   
   only age and number of connections is needed
   
   */
  
  val sc = new SparkContext("local[*]","connections")
  val friendConnections = 
    sc.textFile("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week9_Apache Spark Introduction/friendsdata-201008-180523.csv")
    .map(x => (x.split(",")(2).toInt,x.split(",")(3).toInt))
    .map(x => (x._1,(x._2,1)))
    .reduceByKey((x,y) => (x._1 + y._1, x._2 + y._2))
    .map(x => (x._1,(x._2._1/x._2._2)))
    .sortBy(x => x._2,false)
    .collect
    
    val friendConnections2 = 
    sc.textFile("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week9_Apache Spark Introduction/friendsdata-201008-180523.csv")
    .map(x => (x.split(",")(2).toInt,x.split(",")(3).toInt))
    .mapValues(x => (x,1))
    //.map(x => (x._1,(x._2,1)))
    .reduceByKey((x,y) => (x._1 + y._1, x._2 + y._2))
    .mapValues(x => (x._1/x._2))
    //.map(x => (x._1,(x._2._1/x._2._2)))
    .sortBy(x => x._2,false)
    .collect
    
    friendConnections.foreach(println)
    friendConnections2.foreach(println)
    scala.io.StdIn.readLine()
    
  
}