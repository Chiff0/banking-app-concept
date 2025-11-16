package org.acme;

import jakarta.ws.rs.*;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.transaction.Transactional;


@Path("/users")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GreetingResource 
{

    @GET
    @Path("/list")
    public String listPersons() 
    {
        List<Person> allPersons = Person.listAll();
        return allPersons.toString();
    }

    @POST
    @Transactional
    @Path("/create")
    public Person createUser(createPersonRequest request)
    {
      Person person = new Person();
      person = request.toPerson(person);
      person.persist();

      return person;
    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    public Person updatePerson(@PathParam("id") int id, createPersonRequest request)
    {
      Person person = Person.findById(id);
      if (person != null)
      {
        person = request.toPerson(person);
      }

      return person;
    }

}
