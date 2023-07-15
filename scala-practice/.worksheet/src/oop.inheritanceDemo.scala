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
  };import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(331); 
  
  //create an instance to cat
  val cat = new Cat();System.out.println("""cat  : oop.inheritanceDemo.Cat = """ + $show(cat ));$skip(20); ;
  println(cat.eat);$skip(30); ;
  println(cat.preferredMeal);}
  
  
}
