package org.acme;

import java.util.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Customer extends PanacheEntity
{
  public String name;
  public LocalDate dateOfBirth;
  public String address;
  public String IDNumber;

  @OneToMany(mappedBy = "owner")
  public List<Account> accounts;
}

