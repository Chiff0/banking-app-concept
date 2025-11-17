package org.acme;

import java.math.BigDecimal;

@Embeddable
public class Money
{
  public BigDecimal amount;
  public Currency currency;

  public void add(Money money)
  {
    if (money.currency == this.currency)
    {
      this.amount += money.amount;
    }
    else 
    {
      @Inject CurrencyConverter converter;
      money = converter.convert(money, this.currency);
      this.amount += money.amount;
    }
  }
  public void subtract(Money money)
  {
    if (money.currency == this.currency)
    {
      if (this.amount >= money.amount)
      {
        this.amount -= money.amount;

      }
    }
    else 
    {
      @Inject CurrencyConverter converter;
      money = converter.convert(money, this.currency);
      if (this.amount >= money.amount)
      {
        this.amount -= money.amount;
      }
    }
  }
  
  public void convert(Currency currency)
  {
    @Inject CurrencyConverter converter;
    
    this = converter.convert(this, currency);
  }
}
