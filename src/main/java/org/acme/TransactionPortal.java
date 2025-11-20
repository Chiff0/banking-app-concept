package org.acme;

import jakarta.ws.rs.*;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.enterprise.context.*;
import java.util.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.transaction.Transactional;

@Transactional
@Path("/API")
@ApplicationScoped
public class TransactionPortal
{
  @POST
  @Path("/deposit")
  public TransactionHistory deposit(DepositRequest request)
  {
    TransactionHistory newTransaction = new TransactionHistory();
    Account destination = Account.findByAccountNumber(request.accountNumber);
    newTransaction.type = TransactionType.Deposit; 
    newTransaction.amount = request.amount;
    
    if (destination != null)
    {
      newTransaction.accountTo = destination;
      newTransaction.status = TransactionStatus.Completed;
      destination.balance.amount += request.amount.amount;
    }
    else 
    {
      newTransaction.status = TransactionStatus.Declined;
    }
    
    newTransaction.persist();
    return newTransaction; 
  }
}



