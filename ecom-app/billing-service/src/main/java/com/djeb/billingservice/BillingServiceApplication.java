package com.djeb.billingservice;

import com.djeb.billingservice.entities.Bill;
import com.djeb.billingservice.entities.ProductItem;
import com.djeb.billingservice.model.Customer;
import com.djeb.billingservice.model.Product;
import com.djeb.billingservice.repository.BillRepository;
import com.djeb.billingservice.repository.ProductItemRepository;
import com.djeb.billingservice.services.CustomerRestClient;
import com.djeb.billingservice.services.ProductRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;
//
//@SpringBootApplication
//@EnableFeignClients
//public class BillingServiceApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(BillingServiceApplication.class, args);
//    }
//
//    @Bean
//    CommandLineRunner start(BillRepository billRepository, //Cette Interface permet de communiquer avec la BDD
//                            ProductItemRepository productItemRepository, //Cette Interface permet de communiquer avec la BDD
//                            CustomerRestClient customerRestClient, //Cette Interface permet de communiquer avec la Customer Setrvice
//                            ProductRestClient productRestClient){ //Cette Interface permet de communiquer avec la Product Setrvice
//        return args -> {
//            Collection<Product> products=productRestClient.allProducts().getContent();
//            Long customerId=1L; // 1L : Constante de type Long
//            //Verifier que le customer existe
//            Customer customer=customerRestClient.findCustomerById(customerId);
//            if (customer==null) throw new RuntimeException("Customer not found");
//            Bill bill = new Bill();
//            bill.setBillDate(new Date());
//            bill.setCustomerId(customerId);
//            Bill saveBill = billRepository.save(bill);
//            products.forEach(product -> {
//                ProductItem productItem = new ProductItem();
//                productItem.setBill(saveBill);
//                productItem.setQuantity(1+new Random().nextInt(10));
//                productItem.setPrice(product.getPrice());
//                productItem.setDiscount(Math.random());
//                productItemRepository.save(productItem);
//            });
//        };
//    }
//
//}

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(BillRepository billRepository,
                            ProductItemRepository productItemRepository,
                            CustomerRestClient customerRestClient,
                            ProductRestClient productRestClient){
        return args -> {
            Collection<Product> products=productRestClient.allProducts().getContent();
            Long customerId=1L;
            Customer customer=customerRestClient.findCustomerById(customerId);
            if(customer==null) throw new RuntimeException("Customer not found");
            Bill bill=new Bill();
            bill.setBillDate(new Date());
            bill.setCustomerId(customerId);
            Bill savedBill = billRepository.save(bill);
            products.forEach(product -> {
                ProductItem productItem=new ProductItem();
                productItem.setBill(savedBill);
                productItem.setProductId(product.getId());
                productItem.setQuantity(1+new Random().nextInt(10));
                productItem.setPrice(product.getPrice());
                productItem.setDiscount(Math.random());
                productItemRepository.save(productItem);
            });

        };
    }
}