object ScalaLearning3 {

	def squareIt(x: Int): Int = {
		x*x;
	}                                         //> squareIt: (x: Int)Int

	def cubeIt(x: Int)= x*x*x                 //> cubeIt: (x: Int)Int
		
	//Function as a parameter
	def transformInt(x:Int,f:Int => Int):Int = {
		f(x);
	}                                         //> transformInt: (x: Int, f: Int => Int)Int
	
	println(squareIt(2));                     //> 4
	println(cubeIt(2));                       //> 8

	var res1 = transformInt(2, squareIt);     //> res1  : Int = 4
	var res2 = transformInt(2, cubeIt);       //> res2  : Int = 8
	
	//Anonymous Function - Function without name
	
	var res3 = transformInt(2, x => x*x*x);   //> res3  : Int = 8
	var res4 = transformInt(2, x => x*x);     //> res4  : Int = 4
	
	def divideByTwo(x:Int) = {
		x/2
	}                                         //> divideByTwo: (x: Int)Int
	
	var res5 = divideByTwo(4);                //> res5  : Int = 2

	var res6 = transformInt(4,x => x/2);      //> res6  : Int = 2
	
	var res7 = transformInt(2,x =>{ val y = x * 2 ; y * y })
                                                  //> res7  : Int = 16

}