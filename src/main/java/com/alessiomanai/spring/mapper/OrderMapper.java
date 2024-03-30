package com.alessiomanai.spring.mapper;


import com.alessiomanai.spring.model.Order;
import com.alessiomanai.spring.entity.OrderEntity;
import com.alessiomanai.spring.model.request.OrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper( OrderMapper.class );

    OrderEntity fromDTOtoEntity(Order order);

    Order fromEntityToDTO(OrderEntity entity);

    Order fromRequestToModel(OrderRequest request);

    List<Order> fromEntityToDTOList(List<OrderEntity> c);
}
