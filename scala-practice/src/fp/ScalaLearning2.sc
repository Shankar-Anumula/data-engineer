object ScalaLearning2 {
  /********** Interpolation - s, f and raw *************/
  
  var name: String = "Shankar";                   //> name  : String = Shankar
  val pi: Float = 3.1216f;                        //> pi  : Float = 3.1216
  
  //S Interpolation
  println("Hello $name, How are you?");           //> Hello $name, How are you?
	println(s"Hello $name, How are you?");    //> Hello Shankar, How are you?
	
	//f Interpolation
	println(s"Value of pi is $pi");           //> Value of pi is 3.1216
	println(f"Value of pi is $pi%.2f");       //> Value of pi is 3.12
		
	//raw Interpolation
	
	println("Hello how \n are you?");         //> Hello how 
                                                  //|  are you?
	println(raw"Hello how \n are you?");      //> Hello how \n are you?
	
	//Type Inference
	val abc = 1 < 2;                          //> abc  : Boolean = true
	println(abc);                             //> true

	//String Comparison
	val x: String = "Shankar";                //> x  : String = Shankar
	val y: String = "Shankar";                //> y  : String = Shankar
	
	val z = x==y;                             //> z  : Boolean = true


	/*********************** Flow statements -> IF ELSE ***********************/
	
	if(1>3)
		println ("Hello");
	else
		println("There");                 //> There
		
	/********************** MATCH CASE ***************************/
	val num =1;                               //> num  : Int = 1
	
	num match{
		case 1 => println("one");
		case 2 => println("Two");
		case 3 => println("Three");
		case _ => println("something else");
	}                                         //> one


	/******************** FOR LOOP *******************************/
	
	for(x <- 1 to 10){
		val squared = x*x;
		println (x);
	}                                         //> 1
                                                  //| 2
                                                  //| 3
                                                  //| 4
                                                  //| 5
                                                  //| 6
                                                  //| 7
                                                  //| 8
                                                  //| 9
                                                  //| 10
	

	/************************ WHILE LOOP *************************/
	
	var i = 0;                                //> i  : Int = 0
	
	while (i <= 5){
		println(i);
		i=i+1;
	}                                         //> 0
                                                  //| 1
                                                  //| 2
                                                  //| 3
                                                  //| 4
                                                  //| 5
	
	/********************** DO WHILE *********************/
	
	i = 0;
	
	do{
		println(i);
		i=i+1;
	} while (i <= 5);                         //> 0
                                                  //| 1
                                                  //| 2
                                                  //| 3
                                                  //| 4
                                                  //| 5
                                                  
  /************************ BLOCK OF CODE **********************/
  
  val var1 = {
  	var bl = 10
  	bl + 20
  	//7
  	6.35f
  }                                               //> var1  : Float = 6.35

}