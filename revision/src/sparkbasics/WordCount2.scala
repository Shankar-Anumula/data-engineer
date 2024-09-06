package sparkbasics

import org.apache.spark.SparkContext

object WordCount2 extends App{
  
   val sc = new SparkContext("local[*]","wordcount2")
   // val input = sc.textFile("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week9_Apache Spark Introduction/search_data-201008-180523.txt")
    //val words = input.flatMap(x => x.split(" "))
    //val wordMap = words.map(x =>(x,1))
    //val finalCount = wordMap.reduceByKey((x,y) => (x+y))
    //finalCount.collect().foreach(println)
    
    
  //Modifications
	//1. Count ignoring the case (used lowercase)
	//2. Use Placeholder syntax
	//3. Sort by count of words Descending
  //4. Filter words greater than 10
 
    
    //val results = sc.textFile("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week9_Apache Spark Introduction/search_data-201008-180523.txt")
    //                .flatMap(x => x.split(" "))
    //                .map(x => x.toLowerCase())
    //                .map(x => (x,1))
    //                .reduceByKey((x,y) => x+y)
    //                .map(x => (x._2,x._1))
    //                .sortByKey(false)
    //                .collect.foreach(println)
    
    val results = sc.textFile("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week9_Apache Spark Introduction/search_data-201008-180523.txt")
                    .flatMap(_.split(" "))
                    .map(_.toLowerCase())
                    .map((_,1))
                    .reduceByKey(_+_)
                    //.map(x => (x._2,x._1))
                    //.sortByKey(false)
                    //.sortBy(x => x._2, false)
                    .sortBy(_._2, false)
                    .collect//.foreach(println)
                  
                 
    for(result <- results){
           val word = result._1
           val count = result._2.toInt
                  
           if(count > 10)
             println(s"$word:$count")
                  
    }

    scala.io.StdIn.readLine()
  
  
  
}