package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.util.*;
import java.math.BigDecimal;



@Entity
public class Account extends PanacheEntity
{
  public String name;
  public int age;
  public boolean alive;
  public Status status;
  public BigDecimal balance;
  public AccountType type;


  public static Account findByName(String name)
  {
    return find("name", name).firstResult();
  }

  public static List<Account> findMarried()
  {
    return find("status", Status.Married).list();
  }
  
}
