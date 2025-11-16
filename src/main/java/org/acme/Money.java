package org.acme;

import java.math.BigDecimal;

@Embeddable
public class Money
{
  public BigDecimal amount;
  public Currency currency;

  public add(Money money)
  {
    if (money.currency == this.currency)
    {
      return money.amount + this.amount;
    }
    else 
    {
      this = this.convert(money);
      return money.amount + this.amount;
    }
  }

  public convert(Money money)
  {
    // TODO: Implement the conversion part
  }

}
