object ScalaLearning1 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(48); 
  println("hello there");$skip(104); 
  
  /***************** VARIABLES (var) vs CONSTANTS (val) ****************/
  val a: String = "Hello";System.out.println("""a  : String = """ + $show(a ));$skip(27); ;
  var b: String = "Hello";System.out.println("""b  : String = """ + $show(b ));$skip(17); ;
  
  println(a);$skip(14); ;
  println(b);$skip(98); ;
  
  //a= a + "There";                ------------> Error: reasignment to val
  b = b + " There";$skip(17); ;
  
  println(b);$skip(126); ;
  
  
  /****************************** DATA TYPES *************************/
  
  //Type Inference
  var numberOne : Int = 5;System.out.println("""numberOne  : Int = """ + $show(numberOne ));$skip(29); 
  var numberTwo = 1234567890;System.out.println("""numberTwo  : Int = """ + $show(numberTwo ));$skip(49); 
	
	var numberLongOne : Long = 12345678901234567L;System.out.println("""numberLongOne  : Long = """ + $show(numberLongOne ));$skip(32); 
	var numberByteOne : Byte = 127;System.out.println("""numberByteOne  : Byte = """ + $show(numberByteOne ));$skip(26); 
	
	val c : Boolean = true;System.out.println("""c  : Boolean = """ + $show(c ));$skip(14); 
	val d = true;System.out.println("""d  : Boolean = """ + $show(d ));$skip(21); 
	
	val e: Char = 'a';System.out.println("""e  : Char = """ + $show(e ));$skip(13); 
	val f = 'a';System.out.println("""f  : Char = """ + $show(f ));$skip(43); 

	val piSinglePrecision : Float = 3.1415f;System.out.println("""piSinglePrecision  : Float = """ + $show(piSinglePrecision ));$skip(25); 
	val pi: Double = 3.1415;System.out.println("""pi  : Double = """ + $show(pi ));$skip(27); 

	val g: String = "Hello";System.out.println("""g  : String = """ + $show(g ));$skip(27); ;
  var h: String = "There";System.out.println("""h  : String = """ + $show(h ));$skip(120); ;
  
  
  println ("combined results:"+numberOne+numberTwo+numberLongOne+numberByteOne+c+d+e+f+piSinglePrecision+pi+f+g);}
  
  
 }
