package com.alessiomanai.spring.mapper;

import com.alessiomanai.spring.entity.AddressEntity;
import com.alessiomanai.spring.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper( AddressMapper.class );

    AddressEntity fromDTOtoEntity(Address order);

    Address fromEntityToDTO(AddressEntity entity);
}
