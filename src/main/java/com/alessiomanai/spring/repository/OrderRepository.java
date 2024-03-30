package com.alessiomanai.spring.repository;

import com.alessiomanai.spring.entity.ClientEntity;
import com.alessiomanai.spring.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    Optional<OrderEntity> findByNumber(String order);

    Optional<List<OrderEntity>> findByClient(ClientEntity client);

}
