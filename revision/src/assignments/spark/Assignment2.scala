package assignments.spark

import org.apache.spark.SparkContext


object Assignment2 extends App{
    
  
  val sc = new SparkContext("local[*]","spark in depth")
  sc.setLogLevel("ERROR")
  
  /*
   * Dataset : 
   * 			Views - userId	chapterId	dateAndTime
   * 			Chapters - chapterId	courseId
   * 			Titles - courseId	title
   */
  
  val chaptersRDD = sc.textFile("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week10_Spark Indepth/Assignments/chapters*.csv")
                      .map(x => (x.split(",")(0).toInt,x.split(",")(1).toInt))
                      
  val viewsRDD = sc.textFile("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week10_Spark Indepth/Assignments/views*.csv")
                   .map(x => (x.split(",")(0).toInt,x.split(",")(1).toInt))
                   
  val titlesRDD = sc.textFile("C:/All_WorkSpace/Data-Engineering/Trendy Tech/Week10_Spark Indepth/Assignments/titles*.csv")
                    .map(x => (x.split(",")(0).toInt,x.split(",")(1)))
  
  //Exercise: Number of chapters per course 
  //Chapters - chapterId	courseId
  val chapterCountRDD = chaptersRDD
                                    .map(x => (x._2,1))
                                    .reduceByKey((x,y) => (x+y))
  
  println("Excercise1: Number of chapters per course -------------------->")                                  
  chapterCountRDD.take(20).foreach(println)                                  
   
  //Exercise 2: Removing duplicate views from viewsRDD and making chapterid as key
  //Views - 	chapterId    userId                              
  val distinctViewsRDD = viewsRDD.distinct()
                                 .map(x => (x._2, x._1))
  
  //Joining by keys to include courseid
  //chapterId, (userId, courseId)
  val rdd1 = viewsRDD.join(chaptersRDD)
  
  //Dropping ChapterIds - (userId, courseId),1
  val rdd2 = rdd1.map(x => ((x._2._1,x._2._2),1))
  
  //count the views
  val userPerCourseViewRDD = rdd2.reduceByKey((x,y) => (x+y))
   
  //drop the userid
  val courseViewsCountRDD = userPerCourseViewRDD.map(x => (x._1._2,x._2))
  
  //Join with courseid
  val newJoinedRDD = courseViewsCountRDD.join(chapterCountRDD)
  
  newJoinedRDD.take(20).foreach(println)
  
  //calculate chapeter completion
  val courseCompletionpercentRDD = newJoinedRDD.mapValues(x => (x._1.toDouble/x._2))  
  
  val scoresRDD =  courseCompletionpercentRDD.mapValues (x => { 
    if(x >= 0.9  )  10l              
    else if(x >=  0.5 &&  x < 0.9  )  4l  
    else if(x >=  0.25 &&  x < 0.5) 2l  
    else 0l 
    }
  )  

  val totalScorePerCourseRDD =   scoresRDD.reduceByKey((V1,V2) => V1 + V2)
  
  println("\n Excercise2: Total Scores per course -------------------->")   
  totalScorePerCourseRDD.take(20).foreach(println)

  
  //Exercise 3: Associate Titles with Courses and getting rid of courseIDs
  //Joining the title
  val popularCoursesRDD = totalScorePerCourseRDD.join(titlesRDD)
                                                .map(x => (x._2._1,x._2._2))
                                                .sortByKey(false)
  
  
    println("Excercise3: Popular Course Titles -------------------->")  
    popularCoursesRDD.take(20).foreach(println)

  
  
}