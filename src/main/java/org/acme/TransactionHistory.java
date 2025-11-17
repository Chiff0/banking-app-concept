package org.acme;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import io.quarkus.hibernate.orm.panache.PanacheEntity;


@Entity
public class TransactionHistory extends PanacheEntity
{
  @ManyToOne public Account accountFrom;
  @ManyToOne public Account accountTo;
  public Money amount;
  @Enumerated(EnumType.STRING)
  public TransactionType type;
  @Enumerated(EnumType.STRING)
  public TransactionStatus status; 
  public LocalDateTime timestamp = LocalDateTime.now();
}
