package oop

object caseClassDemo {
  case class Person(name:String, age:Int);
  val person1 = new Person("Shankar",32);         //> person1  : oop.caseClassDemo.Person = Person(Shankar,32)
  
  //1. In case class, class paramaters are automatically promoted to fields
  //	 In a normal class, class parameters need to be promoted to class fields by adding val/var keywords
  println(person1.name);                          //> Shankar
  
  //2. Case classes are sensible toString
  //Instead of throwing the reference name, returns the object details
  println(person1.toString);                      //> Person(Shankar,32)
  println(person1);                               //> Person(Shankar,32)
  
  //3. Equals and hashCode methods implemented already
  //			== Normal class compares the references by default
  //			== In case class - cryptic values are compared
  
  val person2 = new Person("Shankar",32);         //> person2  : oop.caseClassDemo.Person = Person(Shankar,32)
  println(person1 == person2);                    //> true
  
  //4. have companion objects already, we donot have to create it
  //		we can use apply method to build the object
  val person3 = Person.apply("Shankar",32);       //> person3  : oop.caseClassDemo.Person = Person(Shankar,32)
  println(person3);                               //> Person(Shankar,32)
  
  //5. case classes have a handy copy method
  val person5 = person2.copy();                   //> person5  : oop.caseClassDemo.Person = Person(Shankar,32)
  println(person5.age);                           //> 32
  
  //6. case classes are serializable - can be transferred over network
  //In a distributed system, this is an important and most usable feature
  
  
  
}