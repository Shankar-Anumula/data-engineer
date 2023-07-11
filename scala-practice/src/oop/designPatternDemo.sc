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
  	
  }
  
  //Access class-evel functionality directly with the class/object name
  println(Person.N_EYES);                         //> 2
  
  //create an instance and access instance level items
  
  val person1 = new Person("Shankar", 32);        //> person1  : oop.designPatternDemo.Person = oop.designPatternDemo$Person@3fb6a
                                                  //| 447
  
  println("name is :"+person1.name);              //> name is :Shankar
  println("age is :"+person1.age);                //> age is :32
  println("Revised Salary is : "+person1.salaryDoubler(2451000));
                                                  //> Revised Salary is : 4902000
  
  //companion design pattern
  val person2 = new Person("Sumit", 30);          //> person2  : oop.designPatternDemo.Person = oop.designPatternDemo$Person@79b4d
                                                  //| 0f
  val person3 = new Person("Shankar", 32);        //> person3  : oop.designPatternDemo.Person = oop.designPatternDemo$Person@6b2fa
                                                  //| d11
  
  println(person2 == person3);                    //> false
  println(person1 == person3);                    //> false
  
  
  //Singleton Design pattern
  val mary = Person;                              //> mary  : oop.designPatternDemo.Person.type = oop.designPatternDemo$Person$@79
                                                  //| 698539
  val john = Person;                              //> john  : oop.designPatternDemo.Person.type = oop.designPatternDemo$Person$@79
                                                  //| 698539
  println(mary == john);                          //> true
  
}