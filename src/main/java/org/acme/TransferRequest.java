package org.acme;
import java.math.BigDecimal;

public class TransferRequest
{
  Long fromID;
  Long toID;
  Money amount;

  public Account from()
  {
    Account from = Account.findById(self.fromID);
    return from;
  }
  public Account to()
  {
    Account to = Account.findById(self.fromID);
    return to;
  }
  public Money amount()
  {
    return amount;
  }

}
