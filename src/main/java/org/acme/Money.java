package org.acme;

import jakarta.persistence.Embeddable;
import jakarta.persistence.*;

@Embeddable
public class Money
{
  public double amount;

  @Enumerated(EnumType.STRING)
  public Currency currency;

  protected Money() {}

  public Money(double amount, Currency currency)
  {
    this.amount = amount;
    this.currency = currency;
  }
}
