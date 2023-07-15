object recursionDemo {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(49); 
  var a = "hello";var a=5;System.out.println("""a  : String = """ + $show(a ));System.out.println("""a  : <error> = """ + $show(a ));$skip(137); ;
  
  //Loop
  def factorial(input: Int): Int = {
		var result: Int  =1

		for(I <- 1 to input){
			result = result * I;
		}
		result;
	};System.out.println("""factorial: (input: Int)Int""");$skip(26); 
	
	var f1 = factorial(5);System.out.println("""f1  : Int = """ + $show(f1 ));$skip(115); ;

	//recursion
	
	def factorialRec(input: Int): Int = {
		if(input == 1) 1
		else input * factorialRec(input-1);
	};System.out.println("""factorialRec: (input: Int)Int""");$skip(29); 
	
	var f2 = factorialRec(5);System.out.println("""f2  : Int = """ + $show(f2 ));$skip(159); ;
	
	
	//Tail recursion
	
	def factorialTrailRec(input: Int, result: Int): Int = {
		if(input == 1) result
		else factorialTrailRec(input-1, result * input);
	};System.out.println("""factorialTrailRec: (input: Int, result: Int)Int""");$skip(37); 
	
	var f3  = factorialTrailRec(5,1);System.out.println("""f3  : Int = """ + $show(f3 ));$skip(68); ;
	
	//val a =  println("Hello world");
	
	val x = if(a==5) 2 else 7;;System.out.println("""x  : Int = """ + $show(x ))}
                                                  
                                                
	
}
