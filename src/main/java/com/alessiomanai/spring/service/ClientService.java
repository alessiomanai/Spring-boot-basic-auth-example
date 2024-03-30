package com.alessiomanai.spring.service;

import com.alessiomanai.spring.entity.ClientEntity;
import com.alessiomanai.spring.mapper.ClientMapper;
import com.alessiomanai.spring.repository.ClientRepository;
import com.alessiomanai.spring.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public List<Client> findBy(String name, String surname){

        return ClientMapper.INSTANCE.fromEntityToDTOList(
            clientRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, surname)
        );
    }

    public List<ClientEntity> findClientBy(String name, String surname){

        return clientRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, surname);
    }

}
