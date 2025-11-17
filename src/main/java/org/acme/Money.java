package org.acme;

import jakarta.persistence.Embeddable;

@Embeddable
public class Money
{
  public double amount;
  public Currency currency;

  public Money(double amount, Currency currency)
  {
    this.amount = amount;
    this.currency = currency;
  }
}
