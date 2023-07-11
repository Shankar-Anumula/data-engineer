package assignments.week8scala

object StrictlyLesser {
  def main(args:Array[String]){
    
    val inputLine1:String = readLine();
    val inputLine2:String = readLine();
    
    val numElements:Array[Int] = inputLine1.split(" ").map(x=>x.toInt);
    val allElements:Array[Int] = inputLine2.split(" ").map(_.toInt);
    
    val numK = numElements(1);
    
    var count = 0;
    
    for(i<-allElements){
       if(i<numK)
         count = count+1;
    }
  
    println(count);
  }
}