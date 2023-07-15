object ScalaLearning2 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(116); 
  /********** Interpolation - s, f and raw *************/
  
  var name: String = "Shankar";System.out.println("""name  : String = """ + $show(name ));$skip(27); ;
  val pi: Float = 3.1216f;System.out.println("""pi  : Float = """ + $show(pi ));$skip(63); ;
  
  //S Interpolation
  println("Hello $name, How are you?");$skip(40); ;
	println(s"Hello $name, How are you?");$skip(54); ;
	
	//f Interpolation
	println(s"Value of pi is $pi");$skip(37); ;
	println(f"Value of pi is $pi%.2f");$skip(61); ;
		
	//raw Interpolation
	
	println("Hello how \n are you?");$skip(38); ;
	println(raw"Hello how \n are you?");$skip(38); ;
	
	//Type Inference
	val abc = 1 < 2;System.out.println("""abc  : Boolean = """ + $show(abc ));$skip(15); ;
	println(abc);$skip(50); ;

	//String Comparison
	val x: String = "Shankar";System.out.println("""x  : String = """ + $show(x ));$skip(28); ;
	val y: String = "Shankar";System.out.println("""y  : String = """ + $show(y ));$skip(17); ;
	
	val z = x==y;System.out.println("""z  : Boolean = """ + $show(z ));$skip(138); ;


	/*********************** Flow statements -> IF ELSE ***********************/
	
	if(1>3)
		println ("Hello");
	else
		println("There");$skip(81); ;
		
	/********************** MATCH CASE ***************************/
	val num =1;System.out.println("""num  : Int = """ + $show(num ));$skip(142); ;
	
	num match{
		case 1 => println("one");
		case 2 => println("Two");
		case 3 => println("Three");
		case _ => println("something else");
	};$skip(128); 


	/******************** FOR LOOP *******************************/
	
	for(x <- 1 to 10){
		val squared = x*x;
		println (x);
	};$skip(82); 
	

	/************************ WHILE LOOP *************************/
	
	var i = 0;System.out.println("""i  : Int = """ + $show(i ));$skip(45); ;
	
	while (i <= 5){
		println(i);
		i=i+1;
	};$skip(69); 
	
	/********************** DO WHILE *********************/
	
	i = 0;$skip(49); ;
	
	do{
		println(i);
		i=i+1;
	} while (i <= 5);$skip(181); ;
                                                  
  /************************ BLOCK OF CODE **********************/
  
  val var1 = {
  	var bl = 10
  	bl + 20
  	//7
  	6.35f
  };System.out.println("""var1  : Float = """ + $show(var1 ))}

}
