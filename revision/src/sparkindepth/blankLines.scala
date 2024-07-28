package sparkindepth

import org.apache.spark.SparkContext
import org.apache.log4j.Level
import org.apache.log4j.Logger

object blankLines extends App{
  
  /*
   * Dataset - C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week10_Spark Indepth/samplefile-201014-183159.txt
   * 
   * 
   * 
   */
  
    Logger.getLogger("org").setLevel(Level.ERROR)
  
    val sc = new SparkContext("local[*]","Blank Lines Accumulator")
    val input = sc.textFile("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week10_Spark Indepth/samplefile-201014-183159.txt")
    
    val myaccum = sc.longAccumulator("blank lines accumulator")
    input.foreach(x => if(x=="") myaccum.add(1))
    
    println(myaccum.value)
  
}