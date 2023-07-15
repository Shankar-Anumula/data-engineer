package oop

object caseClassDemo {
  case class Person(name:String, age:Int);import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(120); ;
  val person1 = new Person("Shankar",32);System.out.println("""person1  : oop.caseClassDemo.Person = """ + $show(person1 ));$skip(209); ;
  
  //1. In case class, class paramaters are automatically promoted to fields
  //	 In a normal class, class parameters need to be promoted to class fields by adding val/var keywords
  println(person1.name);$skip(145); ;
  
  //2. Case classes are sensible toString
  //Instead of throwing the reference name, returns the object details
  println(person1.toString);$skip(20); ;
  println(person1);$skip(215); ;
  
  //3. Equals and hashCode methods implemented already
  //			== Normal class compares the references by default
  //			== In case class - cryptic values are compared
  
  val person2 = new Person("Shankar",32);System.out.println("""person2  : oop.caseClassDemo.Person = """ + $show(person2 ));$skip(31); ;
  println(person1 == person2);$skip(163); ;
  
  //4. have companion objects already, we donot have to create it
  //		we can use apply method to build the object
  val person3 = Person.apply("Shankar",32);System.out.println("""person3  : oop.caseClassDemo.Person = """ + $show(person3 ));$skip(20); ;
  println(person3);$skip(80); ;
  
  //5. case classes have a handy copy method
  val person5 = person2.copy();System.out.println("""person5  : oop.caseClassDemo.Person = """ + $show(person5 ));$skip(24); ;
  println(person5.age);}
  
  //6. case classes are serializable - can be transferred over network
  //In a distributed system, this is an important and most usable feature
  
  
  
}
