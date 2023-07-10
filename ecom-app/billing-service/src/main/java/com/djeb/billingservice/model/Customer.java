package com.djeb.billingservice.model;

import lombok.Data;

// @Entity : Il faut pas l'ajouter sinon il va créer une table dans BDD
@Data
public class Customer {
    private Long id;
    private String name;
    private String email;
}
