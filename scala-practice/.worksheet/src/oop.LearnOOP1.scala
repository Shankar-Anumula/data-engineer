package oop

object LearnOOP1 {
  
	//Empty class - without any data and functionality associated
	class EmpPerson;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(140); ;
	val ep = new EmpPerson;System.out.println("""ep  : oop.LearnOOP1.EmpPerson = """ + $show(ep ));$skip(14); ;
	println(ep);
	
	//Class Person wth 2 parameters
	class SamplePerson(val name:String,val Age:Int);$skip(141); ; //constructor
	val sp = new SamplePerson("Shankar",32);System.out.println("""sp  : oop.LearnOOP1.SamplePerson = """ + $show(sp ));$skip(136); ;
	
	//in the above class both name and age are class parameters
	//to promte them to class fields add "val" or var"
	
	println(sp.name);$skip(18); ;
	println(sp.Age);

	//class with parameters, body
	class Person(name:String, age:Int){
		val noOfEyes = 2;
		
		def ageDoubler = age * 2;
		def salaryDoubler(salary:Int) = salary*2;
		
	};$skip(213); 
	
	val person = new Person("Shankar", 32);System.out.println("""person  : oop.LearnOOP1.Person = """ + $show(person ));$skip(27); ;
	println(person.noOfEyes);$skip(29); ;
	println(person.ageDoubler);$skip(40); ;
	println(person.salaryDoubler(300000));}
	
	
	//static in Java - class level functionality

	object Person{
		val N_EYES = 2;
		def canFly:Boolean = false;
	
	}

	
}
