import org.apache.spark.SparkContext


object ConnectionsFunc extends App{
  
  def parseLine(line:String) = {
    
    val fields = line.split(",")
    val age = fields(2).toInt
    val numConnections = fields(3).toInt
    
    (age, numConnections)
  }
  
  val sc = new SparkContext("local[*]","connectionsFunc")
  val input = sc.textFile("C:/All_WorkSpace/Data Engineering/Trendy Tech/Week9_Apache Spark Introduction/friendsdata-201008-180523.csv")
  val mappedInput = input.map(parseLine)
  //(33,100)
  //(33,200)
  //(33,150)
  
  val mappedFinal = mappedInput.mapValues(x => (x,1))
  //val mappedFinal = mappedInput.map(x => (x._1,(x._2,1)))
  
  //(33,(100,1))
  //(33,(200,1))
  //(33,(150,1))
  
  val totalsByAge = mappedFinal.reduceByKey((x,y)=> (x._1+y._1,x._2+y._2))
  //(33, (450,3))
  
  
  //Average is an aggregation should be performed after all the data is available from various nodes. Hence after reduce, map is applied again
  val averageByAge = totalsByAge.mapValues(x => (x._1/x._2))
  //val averageByAge = totalsByAge.map(x => (x._1,x._2._1/x._2._2))
  //(33, 150)
  
  val sortedAverage = averageByAge.sortBy(x => x._2, false)
  
  averageByAge.collect.foreach(println)
  
  
}