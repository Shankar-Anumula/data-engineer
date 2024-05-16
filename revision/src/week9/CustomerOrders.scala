package week9

import org.apache.spark.SparkContext

object CustomerOrders extends App{
  
    /*
     *Find which customer spent maximum amount
     	
     	Customer_id, product_id, amount_spent
     	we dont need product_id to process maximum amount
    
     * 
     */
  
    val sc = new SparkContext("local[*]","customerorders")
    
    val input = sc.textFile("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week9_Apache Spark Introduction/customerorders-201008-180523.csv")
    
    val orders = input.map(x => x.split(","))
    val refinedOrders = orders.map(x => (x(0),x(2).toFloat))
    val customerOrders = refinedOrders.reduceByKey((x,y) => x+y)
    val sortedOrders = customerOrders.sortBy(x => x._2,false)
    
    
    sortedOrders.collect.foreach(println)
    
    scala.io.StdIn.readLine()
    
}