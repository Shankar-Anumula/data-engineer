object recursionDemo {
  var a = "hello";var a=5;
  
  //Loop
  def factorial(input: Int): Int = {
		var result: Int  =1

		for(I <- 1 to input){
			result = result * I;
		}
		result;
	}
	
	var f1 = factorial(5);

	//recursion
	
	def factorialRec(input: Int): Int = {
		if(input == 1) 1
		else input * factorialRec(input-1);
	}
	
	var f2 = factorialRec(5);
	
	
	//Tail recursion
	
	def factorialTrailRec(input: Int, result: Int): Int = {
		if(input == 1) result
		else factorialTrailRec(input-1, result * input);
	}
	
	var f3  = factorialTrailRec(5,1);
	
	//val a =  println("Hello world");
	
	val x = if(a==5) 2 else 7;
                                                  
                                                
	
}