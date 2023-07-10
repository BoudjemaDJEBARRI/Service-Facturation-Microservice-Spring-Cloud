package com.djeb.billingservice.web;

import com.djeb.billingservice.entities.Bill;
import com.djeb.billingservice.repository.BillRepository;
import com.djeb.billingservice.repository.ProductItemRepository;
import com.djeb.billingservice.services.CustomerRestClient;
import com.djeb.billingservice.services.ProductRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillRestController {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private CustomerRestClient customerRestClient;
    @Autowired  //  @Autowired = Contructor avec paramettre
    private ProductRestClient productRestClient;

    @GetMapping("/fullBill/{id}")
    public Bill bill(@PathVariable Long id){
        Bill bill=billRepository.findById(id).get();
        bill.setCustomer(customerRestClient.findCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(pi->{
            pi.setProduct(productRestClient.findProductById(pi.getProductId()));
        });
        return bill;
    }
}
