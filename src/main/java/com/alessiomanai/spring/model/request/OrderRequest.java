package com.alessiomanai.spring.model.request;

import com.alessiomanai.spring.model.Address;
import com.alessiomanai.spring.model.Client;
import lombok.Data;

@Data
public class OrderRequest {
    private Address deliveryAddress;
    private Client client;
    private int pizza;
}
