object ScalaLearning3 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(65); 

	def squareIt(x: Int): Int = {
		x*x;
	};System.out.println("""squareIt: (x: Int)Int""");$skip(28); 

	def cubeIt(x: Int)= x*x*x;System.out.println("""cubeIt: (x: Int)Int""");$skip(87); 
		
	//Function as a parameter
	def transformInt(x:Int,f:Int => Int):Int = {
		f(x);
	};System.out.println("""transformInt: (x: Int, f: Int => Int)Int""");$skip(25); 
	
	println(squareIt(2));$skip(21); ;
	println(cubeIt(2));$skip(40); ;

	var res1 = transformInt(2, squareIt);System.out.println("""res1  : Int = """ + $show(res1 ));$skip(37); ;
	var res2 = transformInt(2, cubeIt);System.out.println("""res2  : Int = """ + $show(res2 ));$skip(91); ;
	
	//Anonymous Function - Function without name
	
	var res3 = transformInt(2, x => x*x*x);System.out.println("""res3  : Int = """ + $show(res3 ));$skip(39); ;
	var res4 = transformInt(2, x => x*x);System.out.println("""res4  : Int = """ + $show(res4 ));$skip(39); ;
	
	def divideByTwo(x:Int) = {
		x/2
	};System.out.println("""divideByTwo: (x: Int)Int""");$skip(30); 
	
	var res5 = divideByTwo(4);System.out.println("""res5  : Int = """ + $show(res5 ));$skip(39); ;

	var res6 = transformInt(4,x => x/2);System.out.println("""res6  : Int = """ + $show(res6 ));$skip(60); ;
	
	var res7 = transformInt(2,x =>{ val y = x * 2 ; y * y });System.out.println("""res7  : Int = """ + $show(res7 ))}

}
