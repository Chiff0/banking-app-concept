package org.acme;


public class createPersonRequest
{
  public String name;
  public int age;
  public boolean alive;
  public Status status;

  public Person toPerson(Person person)
  {
    person.name = this.name;
    person.age = this.age;
    person.alive = this.alive;
    person.status = this.status;

    return person;
  }

}

