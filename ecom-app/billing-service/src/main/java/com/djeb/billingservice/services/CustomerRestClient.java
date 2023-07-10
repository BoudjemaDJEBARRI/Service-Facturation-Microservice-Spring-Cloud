package com.djeb.billingservice.services;

import com.djeb.billingservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Cette interface va être basée sur Open Feign
// Spring Data est un framework déclaratif contrairement à Open Feign, ça veut dire qu'il fait qu'exposer les interfaces
// FeignClient : permet de comuniquer avec les autres microservice via REST

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {
    @GetMapping(path="/customers/{id}")
    Customer findCustomerById(@PathVariable Long id); // @PathVariable  => {id} = Long id
}

//@FeignClient(name = "CUSTEMER-SERVICE")
//public interface CustomerRestClient {
//    @GetMapping(path = "/customers/{id}")
//    Customer findCustomerById(@PathVariable Long id);
//}