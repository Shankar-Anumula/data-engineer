object ScalaLearning4 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(152); 
  
  /********************** First Class Function **********************/
  
  def doubler (i: Int): Int = {
			return i *2;
		};System.out.println("""doubler: (i: Int)Int""");$skip(65); 
		
	//Function can be assigned to a variable
	var a = doubler(_);System.out.println("""a  : Int => Int = """ + $show(a ));$skip(15); 
	var a1 = a(2);System.out.println("""a1  : Int = """ + $show(a1 ));$skip(79); 
	
	//Function can be passed a parameter
	def tripler (i: Int): Int = { i *3; };System.out.println("""tripler: (i: Int)Int""");$skip(49); 
	
	def func(i: Int, f:Int => Int) = {
		f(i);
	};System.out.println("""func: (i: Int, f: Int => Int)Int""");$skip(30); 
	
	var a2 = func(2, tripler);System.out.println("""a2  : Int = """ + $show(a2 ));$skip(83); ;
	 
	
	//return a function from a function
	
		def func1 = {
			x: Int => x* x;
		};System.out.println("""func1: => Int => Int""");$skip(15); val res$0 = 
		
		func1(4);System.out.println("""res0: Int = """ + $show(res$0));$skip(25); ;
		
		
		var a3 = 1 to 10;System.out.println("""a3  : scala.collection.immutable.Range.Inclusive = """ + $show(a3 ));$skip(18); val res$1 = 
		a3.map(doubler);System.out.println("""res1: scala.collection.immutable.IndexedSeq[Int] = """ + $show(res$1))}
		
}
