package com.alessiomanai.spring.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class OrderEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String number;

  @ManyToOne
  private AddressEntity deliveryAddress;

  @ManyToOne(cascade = CascadeType.ALL)
  private ClientEntity client;

  private int pizza;
  private double orderTotal;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd hh:mm:ss")
  private Date orderDate;
}
