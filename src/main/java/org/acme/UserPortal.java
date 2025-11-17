package org.acme;

import jakarta.ws.rs.*;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.enterprise.context.*;
import java.util.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.transaction.Transactional;


@Path("/users")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserPortal 
{

    @GET
    public List<Person> listPersons() 
    {
        List<Account> allAccounts = Account.listAll();
        return allAccounts;
    }

    @POST
    @Transactional
    public Account createUser(AccountRequest account)
    {
      Account account = new Account();
      account = request.toAccount(account);
      account.persist();

      return account;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Account updateAccount(@PathParam("id") long id, AccountRequest request)
    {
      Account account = Account.findById(id);
      if (account != null)
      {
        account = request.toAccount(account);
      }

      return account;
    }
}
