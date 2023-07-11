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
		
	}
	
	val croc = new Crocodile;                 //> croc  : oop.traitDemo.Crocodile = oop.traitDemo$Crocodile@621be5d1
	croc.eat;                                 //> I eat flesh
	croc.preferredMeal;                       //> I like sea food
	
}