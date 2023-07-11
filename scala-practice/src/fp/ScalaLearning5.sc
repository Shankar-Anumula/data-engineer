object ScalaLearning5 {

	def areaOfCircle = {
		val pi = 3.14;
		(x:Int) => pi * x * x;
	}                                         //> areaOfCircle: => Int => Double
	
	val pi = 3.1;                             //> pi  : Double = 3.1
	var result = areaOfCircle(10);            //> result  : Double = 314.0

	val a = 5;                                //> a  : Int = 5
	val b:Int = 6;                            //> b  : Int = 6
	val c = println("Hello");                 //> Hello
                                                  //| c  : Unit = ()
	val d = if(a==b) 1 else "Hello"           //> d  : Any = Hello
	val e = if(a==b) 'a' else 7;              //> e  : Int = 7
	
	val f = a.*(b);                           //> f  : Int = 30
	val g = a * b;                            //> g  : Int = 30
	
	var h = a.compare(b);                     //> h  : Int = -1
	var i = a compare b;                      //> i  : Int = -1
	
	
		val y = 1 to 100                  //> y  : scala.collection.immutable.Range.Inclusive = Range 1 to 100
		val z = y.map((x:Int) => {2 * x});//> z  : scala.collection.immutable.IndexedSeq[Int] = Vector(2, 4, 6, 8, 10, 12,
                                                  //|  14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 48, 50,
                                                  //|  52, 54, 56, 58, 60, 62, 64, 66, 68, 70, 72, 74, 76, 78, 80, 82, 84, 86, 88,
                                                  //|  90, 92, 94, 96, 98, 100, 102, 104, 106, 108, 110, 112, 114, 116, 118, 120, 
                                                  //| 122, 124, 126, 128, 130, 132, 134, 136, 138, 140, 142, 144, 146, 148, 150, 1
                                                  //| 52, 154, 156, 158, 160, 162, 164, 166, 168, 170, 172, 174, 176, 178, 180, 18
                                                  //| 2, 184, 186, 188, 190, 192, 194, 196, 198, 200)
		val j = y.map(_ * 2);             //> j  : scala.collection.immutable.IndexedSeq[Int] = Vector(2, 4, 6, 8, 10, 12,
                                                  //|  14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 48, 50,
                                                  //|  52, 54, 56, 58, 60, 62, 64, 66, 68, 70, 72, 74, 76, 78, 80, 82, 84, 86, 88,
                                                  //|  90, 92, 94, 96, 98, 100, 102, 104, 106, 108, 110, 112, 114, 116, 118, 120, 
                                                  //| 122, 124, 126, 128, 130, 132, 134, 136, 138, 140, 142, 144, 146, 148, 150, 1
                                                  //| 52, 154, 156, 158, 160, 162, 164, 166, 168, 170, 172, 174, 176, 178, 180, 18
                                                  //| 2, 184, 186, 188, 190, 192, 194, 196, 198, 200)
}