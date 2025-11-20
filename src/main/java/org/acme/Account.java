package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.util.*;
import java.math.BigDecimal;



@Entity
public class Account extends PanacheEntity
{
  public Money balance;

  @Enumerated(EnumType.STRING)
  public AccountType type;
  @Enumerated(EnumType.STRING)
  public Status status;

  @Column(unique = true, nullable = false)
  public String accountNumber;

  @ManyToOne
  public Customer owner;

  public static Account findByAccountNumber(String accountNumber)
  {
    return Account.find("accountNumber", accountNumber).firstResult();
  }
}
