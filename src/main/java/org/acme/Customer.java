package org.acme;

import java.util.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Customer extends PanacheEntity
{
  public String name;
  public LocalDate dateOfBirth;
  public String address;
  public String IDNumber;

  @OneToMany(mappedBy = "owner")
  @JsonIgnore
  public List<Account> accounts;

  public static Customer findByIDNumber(String IDNumber)
  {
    return Customer.find("IDNumber", IDNumber).firstResult();
  }
}

