import org.apache.spark.SparkContext
import org.apache.log4j.Level
import org.apache.log4j.Logger


object CustomerOrders extends App{
  
  Logger.getLogger("org").setLevel(Level.INFO)

      
  val sc = new SparkContext("local[*]","customerorders")
  val input = sc.textFile("C:/All_WorkSpace/Data Engineering/Trendy Tech/Week9_Apache Spark Introduction/customerorders-201008-180523.csv")
  
  val customersData = input.map(x => x.split(","))
                           .map(x => (x(0),x(2).toFloat))
                           .reduceByKey((x,y) => x + y)
                           .sortBy(x => x._2)
                           .collect
                           
  customersData.foreach(println)
  
   //make the program wait for user input so that DAG can be visualized in https://localhost:4040
   scala.io.StdIn.readLine()
  
}