package com.alessiomanai.spring.service;

import com.alessiomanai.spring.entity.ClientEntity;
import com.alessiomanai.spring.entity.OrderEntity;
import com.alessiomanai.spring.mapper.AddressMapper;
import com.alessiomanai.spring.mapper.ClientMapper;
import com.alessiomanai.spring.mapper.OrderMapper;
import com.alessiomanai.spring.model.Order;
import com.alessiomanai.spring.model.request.EditOrderRequest;
import com.alessiomanai.spring.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    AddressService addressService;

    @Autowired
    ClientService clientService;

    private Double pizzaPrice = 5.0;

    public Order createOrder(Order order){

        OrderEntity entity = OrderMapper.INSTANCE.fromDTOtoEntity(order);

        entity.setOrderTotal(entity.getPizza() * pizzaPrice);
        entity.setOrderDate(new Date());

        entity.setDeliveryAddress(AddressMapper.INSTANCE.fromDTOtoEntity(order.getDeliveryAddress()));

        entity.setClient(ClientMapper.INSTANCE.fromDTOtoEntity(order.getClient()));

        entity.setDeliveryAddress(
                addressService.checkIfAddressExistOrCreate(order.getDeliveryAddress())
        );

        entity.setNumber(UUID.randomUUID().toString());

        OrderEntity result = orderRepository.save(entity);

        return OrderMapper.INSTANCE.fromEntityToDTO(result);
    }

    private Optional<OrderEntity> getOrderDate(EditOrderRequest order){

        return orderRepository.findByNumber(order.getNumber());

    }

    private Boolean isOrderOlderThan5Mins(EditOrderRequest order){

        Optional<OrderEntity> entity = getOrderDate(order);

        long diffInMillies = Math.abs(new Date().getTime() - entity.get().getOrderDate().getTime());
        long diff = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);

        if(diff >= 5){
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    public String editOrder(EditOrderRequest order){

        Optional<OrderEntity> orderEntity = orderRepository.findByNumber(order.getNumber());

        if(orderEntity.isEmpty()){
            return "Order not found";
        }

        if(isOrderOlderThan5Mins(order)){
            return "Can't edit order";
        }

        orderEntity.get().setOrderTotal(order.getPizza() * pizzaPrice);
        orderEntity.get().setPizza(order.getPizza());

        orderRepository.save(orderEntity.get());

        return "Order edited";
    }

    public List<Order> findByUser(String name, String surname){

        List<ClientEntity> client = clientService.findClientBy(name, surname);
        List<OrderEntity> orders = new ArrayList<>();

        for(ClientEntity c : client){

            Optional<List<OrderEntity>> o = orderRepository.findByClient(c);

            o.ifPresent(orders::addAll);

        }

        return OrderMapper.INSTANCE.fromEntityToDTOList(orders);
    }
}
