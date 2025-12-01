package org.acme;

import jakarta.ws.rs.*;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.enterprise.context.*;
import java.util.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.transaction.Transactional;
import jakarta.inject.Inject;

@Transactional
@Path("/API")
@ApplicationScoped
public class TransactionPortal
{
  @Inject
  CurrencyConverter converter;


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

    @POST
    @Path("/withdraw")
    public TransactionHistory withdraw(WithdrawRequest request)
    {
      TransactionHistory newTransaction = new TransactionHistory();
      Account destination = Account.findByAccountNumber(request.accountNumber);
      newTransaction.type = TransactionType.Withdrawal; 
      newTransaction.amount = request.amount;
      
      if (destination != null)
      {
        newTransaction.accountTo = destination;
        newTransaction.status = TransactionStatus.Completed;
        destination.balance.amount += request.amount.amount;
        
        if (destination.balance.amount >= request.amount.amount)
        {
          destination.balance.amount -= request.amount.amount; 

          newTransaction.status = TransactionStatus.Completed;

          newTransaction.persist();
          return newTransaction;
        }

      }

      newTransaction.status = TransactionStatus.Declined;
      
      
      newTransaction.persist();
      return newTransaction; 
    }


  @POST
  @Path("/transfer")
  public TransactionHistory transfer(TransferRequest request)
  {
    Account source = Account.findByAccountNumber(request.fromID);
    Account destination = Account.findByAccountNumber(request.toID);

    Money sourceSubtract = request.amount;
    Money destinationAdd = request.amount;

    if (source.balance.currency != request.amount.currency)
    {
      sourceSubtract = converter.convert(request.amount, source.balance.currency);
    }

    if (destination.balance.currency != request.amount.currency)
    {
      destinationAdd = converter.convert(request.amount, destination.balance.currency);
    }
    
    TransactionHistory newTransaction = new TransactionHistory();
    newTransaction.type = TransactionType.Transfer;
    newTransaction.amount = request.amount;

    if (source != null && destination != null)
    {
      newTransaction.accountFrom = source;
      newTransaction.accountTo = destination;

      if (source.balance.amount >= sourceSubtract.amount)
      {
        source.balance.amount -= sourceSubtract.amount;
        destination.balance.amount += destinationAdd.amount;

        newTransaction.status = TransactionStatus.Completed;
        
        newTransaction.persist();
        return newTransaction;
      }
    }

    newTransaction.status = TransactionStatus.Declined;

    newTransaction.persist();
    return newTransaction;

  }

}



