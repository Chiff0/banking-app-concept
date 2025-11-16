package org.acme;


public class createPersonRequest
{
  public String name;
  public int age;
  public int id;
  public boolean alive;
  public Status status;

  public Person toPerson(Person person)
  {
    person.name = request.name;
    person.age = request.age;
    person.id = request.id;
    person.alive = request.alive;
    person.status = request.status;

    return person;
  }

}

