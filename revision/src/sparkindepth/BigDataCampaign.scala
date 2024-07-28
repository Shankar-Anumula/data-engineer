package sparkindepth

import org.apache.spark.SparkContext
import org.apache.log4j.Level
import org.apache.log4j.Logger
import scala.io.Source

object BigDataCampaign extends App{
  
  /* Data Set
   * C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week10_Spark Indepth/bigdatacampaigndata-201014-183159.csv
   * 
   * KEYWORDS: FLATMAPVALUES, BROADCAST, FILTER
   * 
   * STEPS INVOLVED
   * STEP1: Read the dataset for two columns : Search term - 1st column and Amount - 11th column 
   * STEP2: Seperate the words in the search term : flatMapValues with space //Works only on column so read the columns in reverse first
   * STEP3: Ignore the case of the words, so convert everything to lower or upper
   * STEP4: Read the Boring words - words we need to ignore and load them to a set
   * STEP5: Broadcast Boring words and apply filter 
   * STEP6: reduce the output to add the amounts per word
   * STEP7: Sort the output by the amount descending
   */
  
  Logger.getLogger("org").setLevel(Level.ERROR)
  

  val sc = new SparkContext("local[*]","bigdatacampaign")
  val campaignDataRDD = sc.textFile("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week10_Spark Indepth/bigdatacampaigndata-201014-183159.csv")
  
  val mappedData = campaignDataRDD.map(x => (x.split(",")(10).toFloat,x.split(",")(0)))
  val flatMappedData = mappedData.flatMapValues(x => x.split(" "))
  val reverseMappedData = flatMappedData.map(x => (x._2.toLowerCase(),x._1))
  val filteredData = reverseMappedData.filter(x => !boringWordsSet.value(x._1))
  
  def loadBoringWords():Set[String]={
    var boringWords:Set[String] = Set()
    val lines = Source.fromFile("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week10_Spark Indepth/boringwords-201014-183159.txt").getLines() 
    for(line <- lines){
      boringWords += line
    }
    boringWords
  }
  val boringWordsSet = sc.broadcast(loadBoringWords)

  
  
  val reducedData = filteredData.reduceByKey((x,y) => (x+y))
  val sortedData = reducedData.sortBy(x => x._2,false)
  
  sortedData.take(20).foreach(println)
  
}