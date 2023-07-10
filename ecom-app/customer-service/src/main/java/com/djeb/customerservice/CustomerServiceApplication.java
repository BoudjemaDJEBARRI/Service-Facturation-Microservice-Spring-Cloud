package com.djeb.customerservice;

import com.djeb.customerservice.entities.Customer;
import com.djeb.customerservice.repository.CustomerRepository;
import org.aspectj.apache.bcel.Repository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(CustomerRepository customerRepository,
											   RepositoryRestConfiguration repositoryRestConfiguration){
		return args -> {
			repositoryRestConfiguration.exposeIdsFor(Customer.class);
			customerRepository.saveAll(
					List.of(
							Customer.builder().name("Iassa").email("iassa@gmail.com").build(),
							Customer.builder().name("Mussa").email("mussa@gmail.com").build(),
							Customer.builder().name("Khidr").email("khidr@gmail.com").build()
					)
			);
			// Méthode 1
			//customerRepository.findAll().forEach(System.out::println);
			// Méthode 2
			customerRepository.findAll().forEach(c -> {
				System.out.println(c);
			});
		};
	}

}
