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
        List<Person> allPersons = Person.listAll();
        return allPersons;
    }

    @POST
    @Transactional
    public Person createUser(PersonRequest request)
    {
      Person person = new Person();
      person = request.toPerson(person);
      person.persist();

      return person;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Person updatePerson(@PathParam("id") long id, PersonRequest request)
    {
      Person person = Person.findById(id);
      if (person != null)
      {
        person = request.toPerson(person);
      }

      return person;
    }
}
