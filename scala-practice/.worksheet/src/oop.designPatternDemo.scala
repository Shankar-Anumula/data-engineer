package oop

object designPatternDemo {

  object Person{
  	//class-level functionality | same like "static" in java
  	//Single Design Pattern
  	val N_EYES = 2;
  	def canFly:Boolean = false;
  }
  
  class Person(val name: String,val age:Int){
  	//instance-level functionality
  	
  	def salaryDoubler(salary:Int) = salary * 2;
  	
  };import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(441); 
  
  //Access class-evel functionality directly with the class/object name
  println(Person.N_EYES);$skip(104); ;
  
  //create an instance and access instance level items
  
  val person1 = new Person("Shankar", 32);System.out.println("""person1  : oop.designPatternDemo.Person = """ + $show(person1 ));$skip(40); ;
  
  println("name is :"+person1.name);$skip(35); ;
  println("age is :"+person1.age);$skip(66); ;
  println("Revised Salary is : "+person1.salaryDoubler(2451000));$skip(73); ;
  
  //companion design pattern
  val person2 = new Person("Sumit", 30);System.out.println("""person2  : oop.designPatternDemo.Person = """ + $show(person2 ));$skip(43); ;
  val person3 = new Person("Shankar", 32);System.out.println("""person3  : oop.designPatternDemo.Person = """ + $show(person3 ));$skip(34); ;
  
  println(person2 == person3);$skip(31); ;
  println(person1 == person3);$skip(56); ;
  
  
  //Singleton Design pattern
  val mary = Person;System.out.println("""mary  : oop.designPatternDemo.Person.type = """ + $show(mary ));$skip(21); ;
  val john = Person;System.out.println("""john  : oop.designPatternDemo.Person.type = """ + $show(john ));$skip(25); ;
  println(mary == john);}
  
}
