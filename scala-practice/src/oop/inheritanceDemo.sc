package oop

object inheritanceDemo {
  class Animal{
 
  	//Method inside the class
  	def eat = print("Animals eat");
  	private def fly = print("Some animals may fly");
  }
  
  class Cat extends Animal{
  
  	//Methods
  	def preferredMeal= print("Cats prefer milk");
  }
  
  //create an instance to cat
  val cat = new Cat();                            //> cat  : oop.inheritanceDemo.Cat = oop.inheritanceDemo$Cat@621be5d1
  println(cat.eat);                               //> Animals eat()
  println(cat.preferredMeal);                     //> Cats prefer milk()
  
  
}