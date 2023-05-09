package com.coast.brenno.bikestoreback.model;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
public class Greeting {
    @Id
    private Long id;
    private String name;
}
