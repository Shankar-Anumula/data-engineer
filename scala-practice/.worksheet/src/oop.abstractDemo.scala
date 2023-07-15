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
	
	};import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(308); 
	
	val dog = new Dog();System.out.println("""dog  : oop.abstractDemo.Dog = """ + $show(dog ));$skip(28); ;
	println(dog.creatureType);$skip(17); ;
	print(dog.eat);}
	

}
