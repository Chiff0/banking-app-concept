package org.acme;

import jakarta.enterprise.context.*;
import java.util.*;
import io.quarkus.scheduler.Scheduled;
import jakarta.annotation.PostConstruct;
import org.eclipse.microprofile.rest.client.inject.RestClient;

 

@ApplicationScoped
public class CurrencyConverter
{
  @RestClient
  ConverterAPIClient converterClient;


  private Map<Currency, Map<Currency, Double>> rates = new HashMap<>();


  @PostConstruct
  public void init()
  {
    updateRates();
  }

  @Scheduled(every = "5m")
  public void updateRates()
  {
    System.out.println("Updating exchange rates...");

    for (Currency from : Currency.values())
    {
      rates.putIfAbsent(from, new HashMap<>());
      
      try {
        ConverterResponse response = converterClient.getRates(from.toString());

        for (Currency to : Currency.values())
        {
          if(to == from)
          {
            rates.get(from).put(to, 1.0);
          }
          else 
          {
            Double rate = response.rates.get(to.toString());
            
            if (rate != null)
            {
              rates.get(from).put(to, rate);
              System.out.println("Added new rate: " + from.toString() + " -> " + to.toString() + ": " + rate.toString());
            }
          }
        }
      } catch (Exception e) 
      {
        System.err.println("Failed to fetch rates for " + from + ": " + e.getMessage());
      }
    }
  }

  public double getRate(Currency from, Currency to)
  {
    return rates.getOrDefault(from, new HashMap<>()).getOrDefault(to, 1.0);
  }
  public Money convert(Money fromMoney, Currency to)
  {
    Currency from = fromMoney.currency;
    if (from == to)
    {
      return fromMoney;
    }
    else
    {
      double rate = getRate(from, to);
      Money temp = new Money(fromMoney.amount * rate, to);

      return temp;
    }
  }
}

