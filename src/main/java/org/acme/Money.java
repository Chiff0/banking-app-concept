package org.acme;

import jakarta.persistence.Embeddable;

@Embeddable
public class Money
{
  public double amount;
  public Currency currency;
}
