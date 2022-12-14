package com.coast.brenno.bikestoreback.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Greeting {
    @Id
    private Long id;
    private String name;
}
