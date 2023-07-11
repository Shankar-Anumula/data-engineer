object ScalaLearning1 {
  println("hello there")                          //> hello there
  
  /***************** VARIABLES (var) vs CONSTANTS (val) ****************/
  val a: String = "Hello";                        //> a  : String = Hello
  var b: String = "Hello";                        //> b  : String = Hello
  
  println(a);                                     //> Hello
  println(b);                                     //> Hello
  
  //a= a + "There";                ------------> Error: reasignment to val
  b = b + " There";
  
  println(b);                                     //> Hello There
  
  
  /****************************** DATA TYPES *************************/
  
  //Type Inference
  var numberOne : Int = 5                         //> numberOne  : Int = 5
  var numberTwo = 1234567890                      //> numberTwo  : Int = 1234567890
	
	var numberLongOne : Long = 12345678901234567L
                                                  //> numberLongOne  : Long = 12345678901234567
	var numberByteOne : Byte = 127            //> numberByteOne  : Byte = 127
	
	val c : Boolean = true                    //> c  : Boolean = true
	val d = true                              //> d  : Boolean = true
	
	val e: Char = 'a'                         //> e  : Char = a
	val f = 'a'                               //> f  : Char = a

	val piSinglePrecision : Float = 3.1415f   //> piSinglePrecision  : Float = 3.1415
	val pi: Double = 3.1415                   //> pi  : Double = 3.1415

	val g: String = "Hello";                  //> g  : String = Hello
  var h: String = "There";                        //> h  : String = There
  
  
  println ("combined results:"+numberOne+numberTwo+numberLongOne+numberByteOne+c+d+e+f+piSinglePrecision+pi+f+g);
                                                  //> combined results:5123456789012345678901234567127truetrueaa3.14153.1415aHello
                                                  //| 
  
  
 }