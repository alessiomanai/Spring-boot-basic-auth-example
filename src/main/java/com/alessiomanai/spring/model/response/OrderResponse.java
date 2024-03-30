package com.alessiomanai.spring.model.response;

import lombok.Data;

@Data
public class OrderResponse {

    private String orderNumber;
    private double orderTotal;
    private String message;

}
