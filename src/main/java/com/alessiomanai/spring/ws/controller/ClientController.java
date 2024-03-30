package com.alessiomanai.spring.ws.controller;

import com.alessiomanai.spring.model.Client;
import com.alessiomanai.spring.service.ClientService;
import jakarta.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping(value = "find")
    public List<Client> findBy(@QueryParam("firstName") String firstName, @QueryParam("lastName") String lastName){


        return clientService.findBy(firstName, lastName);
    }

}
