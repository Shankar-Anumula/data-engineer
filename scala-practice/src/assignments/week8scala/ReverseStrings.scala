package assignments.week8scala

object ReverseStrings {
  def main(args:Array[String]){
    
    val inputLine:String = readLine();
    
    //1st output
    val output1 = inputLine.reverse;
    println(output1);
    
    //2nd output
    val output2:Array[String] = inputLine.split(" ").map(_.reverse);
    println(output2.mkString(" "));
    
    //3rd output
    val output3:Array[String] = inputLine.split(" ").reverse;
    println(output3.mkString(" "));

  }
}