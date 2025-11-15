package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import java.util.*;

/**
 * Example JPA entity defined as a Panache Entity.
 * An ID field of Long type is provided, if you want to define your own ID field extends <code>PanacheEntityBase</code> instead.
 *
 * This uses the active record pattern, you can also use the repository pattern instead:
 * .
 *
 * Usage (more example on the documentation)
 *
 * {@code
 *     public void doSomething() {
 *         MyEntity entity1 = new MyEntity();
 *         entity1.field = "field-1";
 *         entity1.persist();
 *
 *         List<MyEntity> entities = MyEntity.listAll();
 *     }
 * }
 */

@Entity
public class Person extends PanacheEntity
{
  public String name;
  public int age;
  public int idNumber;
  public bool alive;
  public Status status;

  public static Person findByName (String name)
  {
    return find ("name", name).firstResult();
  }

  public static List<Person> findMarried ()
  {
    return find ("status", Status.Married);
  }
  
}
