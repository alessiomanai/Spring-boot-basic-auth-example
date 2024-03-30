package com.alessiomanai.spring.model;

import lombok.Data;

@Data
public class Order {
  private String number;
  private Address deliveryAddress;
  private Client client;
  private int pizza;
  private double orderTotal;

}
