object ScalaLearning5 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(91); 

	def areaOfCircle = {
		val pi = 3.14;
		(x:Int) => pi * x * x;
	};System.out.println("""areaOfCircle: => Int => Double""");$skip(17); 
	
	val pi = 3.1;System.out.println("""pi  : Double = """ + $show(pi ));$skip(32); ;
	var result = areaOfCircle(10);System.out.println("""result  : Double = """ + $show(result ));$skip(13); ;

	val a = 5;System.out.println("""a  : Int = """ + $show(a ));$skip(16); ;
	val b:Int = 6;System.out.println("""b  : Int = """ + $show(b ));$skip(27); ;
	val c = println("Hello");System.out.println("""c  : Unit = """ + $show(c ));$skip(33); ;
	val d = if(a==b) 1 else "Hello";System.out.println("""d  : Any = """ + $show(d ));$skip(30); 
	val e = if(a==b) 'a' else 7;System.out.println("""e  : Int = """ + $show(e ));$skip(19); ;
	
	val f = a.*(b);System.out.println("""f  : Int = """ + $show(f ));$skip(16); ;
	val g = a * b;System.out.println("""g  : Int = """ + $show(g ));$skip(25); ;
	
	var h = a.compare(b);System.out.println("""h  : Int = """ + $show(h ));$skip(22); ;
	var i = a compare b;System.out.println("""i  : Int = """ + $show(i ));$skip(23); ;
	
	
		val y = 1 to 100;System.out.println("""y  : scala.collection.immutable.Range.Inclusive = """ + $show(y ));$skip(37); 
		val z = y.map((x:Int) => {2 * x});System.out.println("""z  : scala.collection.immutable.IndexedSeq[Int] = """ + $show(z ));$skip(24); ;
		val j = y.map(_ * 2);;System.out.println("""j  : scala.collection.immutable.IndexedSeq[Int] = """ + $show(j ))}
}
