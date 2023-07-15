object ScalaCollections {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(66); 

	lazy val output = {println("Hello");1};System.out.println("""output: => Int = <lazy>""");$skip(27); 
	println("learning Scala");$skip(17); 
	println(output)}
		

/*

  /******************* ARRAY ********************************/
  
  val a = Array(1,2,3,4,5);
  val a1:Array[Int] = Array(1,2,3,4,5);
  
  println(a);
  
  println(a.mkString("|"));

	for(i <- a) println(i);
 	a(2) = 7;
 	
 	println(a.mkString("|"));
                                                  
  val a2 = Array(1,2,3,4,5.0,'a', "Shankar");
          
	/******************* LIST **********************************/
	val b = List(1,2,3,4,5);
	println(b);
	
	println(b.head);
	println(b.tail);
	
	for(i <- b) println(i);

	var res0 = b.reverse;

	var res1 = b.sum;
	
	val b1 = List(1,2,3,4.0,a,"Shankar");
	
	
	/******************** TUPLE *******************************/

	val c =("Shankar", 10000, true);
	
	println(c);
	println(c._1);
	
	//key-value pairs
	
	val c1 = (101, "Shankar");
	val c2 = 101 -> "Shankar"
	
	
	/*********************** RANGE ******************************/
	
	val rng = 1 to 10;
	val rng1 = 1 until 10;

	for(i <- rng) println(i);
	for(i <- rng1) println(i);
	
	
	/************************* SET ********************************/
	
	val zx = Set(1,1,1,2,2,3,3,4,5);
	
	var zxmin = zx.min;
	var zxmax = zx.max;
	var zxsum = zx.sum;
	
	
	/************************** MAP *******************************/
	
	var xy = Map(1 -> "Shankar", 2 -> "Big Data", 3 -> "Lead Engineer");
	println(xy);
	for(i <- xy) println(i);
  println(xy.get(1));

	var xy1 = Map(1 -> "Shankar", 2 -> "Big Data", 2 -> "Lead Engineer");
	println(xy1);

*/

}
