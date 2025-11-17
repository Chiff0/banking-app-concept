package org.acme;

import java.util.Map;
import java.util.EnumMap;
import java.math.BigDecimal;

@ApplicationScoped
public class CurrencyConverter
{

  private static final Map<Currency, Map<Currency, BigDecimal>> exchangeRates = new EnumMap<>(Currency.class);

  
  static 
  {
    for (Currency from : Currency.values())
    {
      exchangeRates.put(from, new EnumMap<>(Currency.class));
    }

      setRate(Currency.EUR, Currency.USD, 1.0825);
      setRate(Currency.EUR, Currency.JPY, 166.42);
      setRate(Currency.EUR, Currency.CNY, 7.7423);
      setRate(Currency.EUR, Currency.PLN, 4.3087);
      setRate(Currency.EUR, Currency.CAD, 1.4991);
      setRate(Currency.EUR, Currency.AUD, 1.6289);
      setRate(Currency.USD, Currency.JPY, 153.70);
      setRate(Currency.USD, Currency.CNY, 7.1532);
      setRate(Currency.USD, Currency.PLN, 3.9804);
      setRate(Currency.USD, Currency.CAD, 1.3852);
      setRate(Currency.USD, Currency.AUD, 1.5050);
      setRate(Currency.JPY, Currency.CNY, 0.0465);
      setRate(Currency.JPY, Currency.PLN, 0.0259);
      setRate(Currency.CNY, Currency.PLN, 0.5567);
      setRate(Currency.CAD, Currency.AUD, 1.0865);

      reverseRates();

    
  }

  static void setRate(Currency from, Currency to, BigDecimal rate)
  {
    exchangeRates.get(from).put(to, rate);
  }
  
  static void reverseRates()
  {
    for (Currency from : Currency.values())
    {
      for (Currency to : Currency.values())
      {
        if (from == to)
        {
          setRate(from, to, 1.0)
        }
        else if (!exchangeRates.get(to).contains(from) && exchangeRates.get(from).contains(to))
        {
          setRate(to, from, 1.0 / exchangeRates.get(to).get(from));
        }
      }
    }
  }

  static Money exchange(Money money, Currency currency)
  {
    Money exchanged = new Money();
    exchanged.Currency = currency;

    rate = exchangeRates.get(money.Currency).get(currency);

    exchanged.amount = rate * money.amount;

    return exchanged;
  }

}

