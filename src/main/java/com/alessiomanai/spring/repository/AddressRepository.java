package com.alessiomanai.spring.repository;

import com.alessiomanai.spring.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {

    Optional<AddressEntity> findByStreetAndPostcodeAndCityAndCountry(String street, String postcode, String city, String country);

}
