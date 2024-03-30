package com.alessiomanai.spring.ws.controller;

import com.alessiomanai.spring.mapper.OrderMapper;
import com.alessiomanai.spring.model.Order;
import com.alessiomanai.spring.model.request.EditOrderRequest;
import com.alessiomanai.spring.model.request.OrderRequest;
import com.alessiomanai.spring.model.response.OrderResponse;
import com.alessiomanai.spring.service.OrderService;
import com.alessiomanai.spring.util.PizzaUtils;
import jakarta.ws.rs.QueryParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping(value = "create")
    public OrderResponse create(@RequestBody OrderRequest newOrder){

        OrderResponse response = new OrderResponse();

        if(!PizzaUtils.validpizzaNumber(newOrder.getPizza())){
            response.setMessage("Choose between 5, 10 or 15 pizza");
            return response;
        }

        Order orderModel = OrderMapper.INSTANCE.fromRequestToModel(newOrder);

        Order orderSent = orderService.createOrder(orderModel);

        response.setOrderNumber(orderSent.getNumber());
        response.setOrderTotal(orderSent.getOrderTotal());
        response.setMessage("Order succesful!");

        return response;
    }

    @PutMapping(value = "edit")
    public String edit(@RequestBody EditOrderRequest order){

        if(order.getNumber() == null || order.getNumber().isEmpty()){

            return "Empty or invalid order number";
        }

        if(!PizzaUtils.validpizzaNumber(order.getPizza())){
            return "Choose between 5, 10 or 15 pizza";
        }

        return orderService.editOrder(order);

    }


    @GetMapping(value = "find")
    public List<Order> findBy(@QueryParam("firstName") String firstName, @QueryParam("lastName") String lastName){

        return orderService.findByUser(firstName, lastName);
    }

}
