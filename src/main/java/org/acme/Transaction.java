package org.acme;

import jakarta.ws.rs.*;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;


@Path("/transaction")
@RequestScoped
@Transactional
public class Transaction
{
  @POST
  // TODO: Need a way to get account number and amount
  @Path("/{amount}")
  public void deposit(AccountRequest request, Money amount)
  {
    Account account = new Account();
    account = request.toAccount(account);
    account.balance = money.add(account.balance);
  }
}
