package org.acme;

import jakarta.ws.rs.*;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;


@Path("/API/transactios")
@RequestScoped
@Transactional
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Transaction
{
  @POST
  @Path("/deposit")
  public void deposit(AccountRequest request, Money amount)
  {
    Account account = new Account();
    account = request.toAccount(account);
    account.balance = money.add(account.balance);
  }

  @PUT
  @Path("/transfer")
  public static void transfer(TransferRequest request)
  { 
    // TODO: Implement transfering
  }
}
