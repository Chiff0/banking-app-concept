package org.acme;

import jakarta.ws.rs.*;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.enterprise.context.*;
import java.util.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.transaction.Transactional;
import java.util.UUID;


@Path("/API")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserPortal 
{

    @GET
    @Path("/users")
    public List<Customer> listAllCustomers() 
    {
        List<Customer> allCustomers = Customer.listAll();
        return allCustomers;
    }

    
    @GET
    @Path("/accounts")
    public List<Account> listAllAccounts() 
    {
        List<Account> allAccounts = Account.listAll();
        return allAccounts;
    }

    

    @POST
    @Transactional
    @Path("/account")
    public Account createAccount(AccountRequest request)
    {
      Account account = new Account();
      account.accountNumber = generateAccountNumber();
      account.owner = Customer.findByIDNumber(request.IDNumber);
      account.type = request.type;
      account.balance = new Money(0.0, request.currency);
      account.status = Status.Active; 


      account.persist();
      return account;
    }

    @POST
    @Transactional
    @Path("/customer")
    public Customer createCustomer(CustomerRequest request)
    {
      Customer customer = new Customer();
      customer.name = request.name;
      customer.dateOfBirth = request.dateOfBirth;
      customer.address = request.address;
      customer.IDNumber = generateCustomerNumber();

      customer.persist();
      return customer;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Account updateAccount(@PathParam("id") long id, AccountRequest request)
    {
      Account account = Account.findById(id);
      if (account != null)
      {
        account.owner = Customer.findByIDNumber(request.IDNumber);
        account.type = request.type;
      }

      return account;
    }

    private String generateAccountNumber() 
    {
      return "ACC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
    private String generateCustomerNumber() 
    {
      return "USR-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

}
