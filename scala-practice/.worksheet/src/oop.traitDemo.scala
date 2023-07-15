package oop

object traitDemo {
  abstract class Animal{
		val creatureType: String;
		def eat;
		
		def sleep = println("Animal Class - Animals sleep at different times");
	}

	//Multiple Inheritance with Traits
	
	trait Carnivore{
		def preferredMeal;
	}
	
	trait ColdBlooded;
	
	class Crocodile extends Animal with Carnivore with ColdBlooded{
	
		val creatureType:String = "Canine";
		def eat = println("I eat flesh");
		def preferredMeal = println("I like sea food");
		
	};import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(506); 
	
	val croc = new Crocodile;System.out.println("""croc  : oop.traitDemo.Crocodile = """ + $show(croc ));$skip(11); ;
	croc.eat;$skip(21); ;
	croc.preferredMeal;}
	
}
