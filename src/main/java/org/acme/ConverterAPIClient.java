package org.acme;

import jakarta.ws.rs.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
public interface ConverterAPIClient
{
  @GET
  @Path("/latest")
  ConverterResponse getRates(@QueryParam("from") String currency);
}
