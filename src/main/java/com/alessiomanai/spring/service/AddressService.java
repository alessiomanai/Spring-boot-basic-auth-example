package com.alessiomanai.spring.service;

import com.alessiomanai.spring.mapper.AddressMapper;
import com.alessiomanai.spring.repository.AddressRepository;
import com.alessiomanai.spring.entity.AddressEntity;
import com.alessiomanai.spring.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public AddressEntity checkIfAddressExistOrCreate(Address address){

        AddressEntity otFind = AddressMapper.INSTANCE.fromDTOtoEntity(address);

        Optional<AddressEntity> resultEntity =
                addressRepository.findByStreetAndPostcodeAndCityAndCountry(
                        address.getStreet(), address.getPostcode(), address.getCity(), address.getCountry());

        return resultEntity.orElseGet(() -> addressRepository.save(otFind));

    }

}
