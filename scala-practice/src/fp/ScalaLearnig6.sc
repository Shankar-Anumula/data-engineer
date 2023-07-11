object ScalaLearnig6 {

	/***************** Partially applied functions ************************/

  def divideFunc(x: Double, y: Double) = {x/y}    //> divideFunc: (x: Double, y: Double)Double

	var reso = divideFunc(10,2)               //> reso  : Double = 5.0

	val inverse = divideFunc(1,_:Double)      //> inverse  : Double => Double = ScalaLearnig6$$$Lambda$8/940060004@dfd3711
	var res1 = inverse(100);                  //> res1  : Double = 0.01

	def sumFunc(x:Int, y:Int)  ={x+y};        //> sumFunc: (x: Int, y: Int)Int
	
	val increment = sumFunc(1,_:Int);         //> increment  : Int => Int = ScalaLearnig6$$$Lambda$9/1684106402@13fee20c
	var res2 = increment(20);                 //> res2  : Int = 21


	/********************** Funbction Curring ***************************/
	
	def genericSum(x:Int, y:Int, f:Int => Int) = {f(x) + f(y)};
                                                  //> genericSum: (x: Int, y: Int, f: Int => Int)Int
	
	val sum1 = genericSum(4,5,x=>x);          //> sum1  : Int = 9
	val sum2 = genericSum(4,5,x=>x*x);        //> sum2  : Int = 41
	val sum3 = genericSum(4,5,x=>x*x*x);      //> sum3  : Int = 189
	
	val sumOfSquares = genericSum(_:Int,_:Int,x=>x*x);
                                                  //> sumOfSquares  : (Int, Int) => Int = ScalaLearnig6$$$Lambda$13/610984013@6204
                                                  //| 3840
	val sumOfCubes = genericSum(_:Int,_:Int,x=>x*x*x);
                                                  //> sumOfCubes  : (Int, Int) => Int = ScalaLearnig6$$$Lambda$14/1393931310@2ef9b
                                                  //| 8bc
	
	val sq1 = sumOfSquares(3,4);              //> sq1  : Int = 25
	
	//Logical grouping of parameters
	def genericSumCurry( f:Int => Int)(x:Int, y:Int) = {f(x) + f(y)};
                                                  //> genericSumCurry: (f: Int => Int)(x: Int, y: Int)Int
	val sumC1 = genericSumCurry(x=>x*x)(5,4); //> sumC1  : Int = 41
	
	val sumOfCubesCurry = genericSumCurry(x=>x*x*x)_;
                                                  //> sumOfCubesCurry  : (Int, Int) => Int = ScalaLearnig6$$$Lambda$18/1286783232@
                                                  //| 6fb554cc
}