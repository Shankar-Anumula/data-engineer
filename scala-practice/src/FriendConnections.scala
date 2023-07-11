import org.apache.spark.SparkContext


object FriendConnections extends App {
  val sc = new SparkContext("local[*]","connections")
  
  val connectionsData = sc.textFile("C:/All_WorkSpace/Data Engineering/Trendy Tech/Week9_Apache Spark Introduction/friendsdata-201008-180523.csv")
  
  connectionsData.map(x =>( x.split(",")(2).toInt,x.split(",")(3).toInt))
  .map(x=> (x._1,(x._2,1)))
  .reduceByKey((x,y) => (x._1 + y._1, x._2+y._2))
  .map(x => (x._1,x._2._1/x._2._2))
  .sortBy(x =>x._2,false)
  .collect.foreach(println)
  
}