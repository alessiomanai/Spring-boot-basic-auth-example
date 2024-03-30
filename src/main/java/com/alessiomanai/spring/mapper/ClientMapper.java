package com.alessiomanai.spring.mapper;

import com.alessiomanai.spring.entity.ClientEntity;
import com.alessiomanai.spring.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper( ClientMapper.class );

    ClientEntity fromDTOtoEntity(Client c);

    Client fromEntityToDTO(ClientEntity c);

    List<Client> fromEntityToDTOList(List<ClientEntity> c);
}
