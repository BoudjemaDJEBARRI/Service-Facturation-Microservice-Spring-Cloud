package com.djeb.inventoryservice;

import com.djeb.inventoryservice.entities.Product;
import com.djeb.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    //Sans beans ça ne va pas s'exécuter
    @Bean
    CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration repositoryRestConfiguration){
        return args -> {
            repositoryRestConfiguration.exposeIdsFor(Product.class); // Permet d'afficher le ID
            productRepository.saveAll(
                    List.of(
                            Product.builder().name("Computer").quantity(11).price(1445).build(),
                            Product.builder().name("Smartphone").quantity(31).price(145).build(),
                            Product.builder().name("Keyboard").quantity(21).price(10).build(),
                            Product.builder().name("MacBook").quantity(14).price(145).build(),
                            Product.builder().name("Vertual Glass").quantity(21).price(445).build()
                    )
            );
            productRepository.findAll().forEach(p -> {
                System.out.println(p);
            });
        };

    }

}
