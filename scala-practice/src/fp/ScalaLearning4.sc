object ScalaLearning4 {
  
  /********************** First Class Function **********************/
  
  def doubler (i: Int): Int = {
			return i *2;
		}                                 //> doubler: (i: Int)Int
		
	//Function can be assigned to a variable
	var a = doubler(_)                        //> a  : Int => Int = ScalaLearning4$$$Lambda$8/99347477@2ed94a8b
	var a1 = a(2)                             //> a1  : Int = 4
	
	//Function can be passed a parameter
	def tripler (i: Int): Int = { i *3; }     //> tripler: (i: Int)Int
	
	def func(i: Int, f:Int => Int) = {
		f(i);
	}                                         //> func: (i: Int, f: Int => Int)Int
	
	var a2 = func(2, tripler);                //> a2  : Int = 6
	 
	
	//return a function from a function
	
		def func1 = {
			x: Int => x* x;
		}                                 //> func1: => Int => Int
		
		func1(4);                         //> res0: Int = 16
		
		
		var a3 = 1 to 10                  //> a3  : scala.collection.immutable.Range.Inclusive = Range 1 to 10
		a3.map(doubler)                   //> res1: scala.collection.immutable.IndexedSeq[Int] = Vector(2, 4, 6, 8, 10, 12
                                                  //| , 14, 16, 18, 20)
		
}