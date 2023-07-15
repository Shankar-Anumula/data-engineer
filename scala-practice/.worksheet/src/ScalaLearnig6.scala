object ScalaLearnig6 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(145); 

	/***************** Partially applied functions ************************/

  def divideFunc(x: Double, y: Double) = {x/y};System.out.println("""divideFunc: (x: Double, y: Double)Double""");$skip(30); 

	var reso = divideFunc(10,2);System.out.println("""reso  : Double = """ + $show(reso ));$skip(39); 

	val inverse = divideFunc(1,_:Double);System.out.println("""inverse  : Double => Double = """ + $show(inverse ));$skip(26); 
	var res1 = inverse(100);System.out.println("""res1  : Double = """ + $show(res1 ));$skip(37); ;

	def sumFunc(x:Int, y:Int)  ={x+y};System.out.println("""sumFunc: (x: Int, y: Int)Int""");$skip(37); ;
	
	val increment = sumFunc(1,_:Int);System.out.println("""increment  : Int => Int = """ + $show(increment ));$skip(27); ;
	var res2 = increment(20);System.out.println("""res2  : Int = """ + $show(res2 ));$skip(137); ;


	/********************** Funbction Curring ***************************/
	
	def genericSum(x:Int, y:Int, f:Int => Int) = {f(x) + f(y)};System.out.println("""genericSum: (x: Int, y: Int, f: Int => Int)Int""");$skip(36); ;
	
	val sum1 = genericSum(4,5,x=>x);System.out.println("""sum1  : Int = """ + $show(sum1 ));$skip(36); ;
	val sum2 = genericSum(4,5,x=>x*x);System.out.println("""sum2  : Int = """ + $show(sum2 ));$skip(38); ;
	val sum3 = genericSum(4,5,x=>x*x*x);System.out.println("""sum3  : Int = """ + $show(sum3 ));$skip(54); ;
	
	val sumOfSquares = genericSum(_:Int,_:Int,x=>x*x);System.out.println("""sumOfSquares  : (Int, Int) => Int = """ + $show(sumOfSquares ));$skip(52); ;
	val sumOfCubes = genericSum(_:Int,_:Int,x=>x*x*x);System.out.println("""sumOfCubes  : (Int, Int) => Int = """ + $show(sumOfCubes ));$skip(32); ;
	
	val sq1 = sumOfSquares(3,4);System.out.println("""sq1  : Int = """ + $show(sq1 ));$skip(103); ;
	
	//Logical grouping of parameters
	def genericSumCurry( f:Int => Int)(x:Int, y:Int) = {f(x) + f(y)};System.out.println("""genericSumCurry: (f: Int => Int)(x: Int, y: Int)Int""");$skip(43); ;
	val sumC1 = genericSumCurry(x=>x*x)(5,4);System.out.println("""sumC1  : Int = """ + $show(sumC1 ));$skip(53); ;
	
	val sumOfCubesCurry = genericSumCurry(x=>x*x*x)_;;System.out.println("""sumOfCubesCurry  : (Int, Int) => Int = """ + $show(sumOfCubesCurry ))}
}
