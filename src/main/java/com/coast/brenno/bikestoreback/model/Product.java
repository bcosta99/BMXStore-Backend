package com.coast.brenno.bikestoreback.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue

    @Column(name = "product_id")
    private Long id;
    private String name;
    private String category;
    private String description;
    private String color;
    private Integer stock;
    private Integer price;
    private String url;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}