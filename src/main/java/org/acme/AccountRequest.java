package org.acme;


public class AccountRequest
{
  public String name;
  public int age;
  public boolean alive;
  public Status status;

  public Account toAccount(Account account)
  {
    account.name = this.name;
    account.age = this.age;
    account.alive = this.alive;
    account.status = this.status;

    return account;
  }

}

