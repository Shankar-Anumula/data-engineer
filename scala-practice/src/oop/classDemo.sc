package oop

object classDemo {
  
	//Empty class - without any data and functionality associated
	class EmpPerson;
	val ep = new EmpPerson;                   //> ep  : oop.classDemo.EmpPerson = oop.classDemo$EmpPerson@621be5d1
	println(ep);                              //> oop.classDemo$EmpPerson@621be5d1
	
	//Class Person wth 2 parameters
	class SamplePerson(val name:String,val Age:Int); //constructor
	val sp = new SamplePerson("Shankar",32);  //> sp  : oop.classDemo.SamplePerson = oop.classDemo$SamplePerson$1@79b4d0f
	
	//in the above class both name and age are class parameters
	//to promte them to class fields add "val" or var"
	
	println(sp.name);                         //> Shankar
	println(sp.Age);                          //> 32

	//class with parameters, body
	class Person(name:String, age:Int){
		val noOfEyes = 2;
		
		def ageDoubler = age * 2;
		def salaryDoubler(salary:Int) = salary*2;
		
	}
	
	val person = new Person("Shankar", 32);   //> person  : oop.classDemo.Person = oop.classDemo$Person$1@6b2fad11
	println(person.noOfEyes);                 //> 2
	println(person.ageDoubler);               //> 64
	println(person.salaryDoubler(300000));    //> 600000
	
	
	//static in Java - class level functionality

	object Person{
		val N_EYES = 2;
		def canFly:Boolean = false;
	
	}

	
}