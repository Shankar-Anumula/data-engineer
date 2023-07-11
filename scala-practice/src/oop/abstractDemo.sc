package oop

object abstractDemo {
	
	abstract class Animal{
		val creatureType: String;
		def eat;
		
		def sleep = println("Animals sleep at different times");
	
	}

	class Dog extends Animal{
		val creatureType:String = "Canine";
		def eat:Unit = println("Dogs eat biscuits");
	
	}
	
	val dog = new Dog();                      //> dog  : oop.abstractDemo.Dog = oop.abstractDemo$Dog@621be5d1
	println(dog.creatureType);                //> Canine
	print(dog.eat);                           //> Dogs eat biscuits
	

}