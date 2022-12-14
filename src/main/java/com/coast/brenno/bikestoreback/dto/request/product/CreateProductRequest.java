package com.coast.brenno.bikestoreback.dto.request.product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateProductRequest {
    private String name;
    private String category;
    private String description;
    private String color;
    private Integer stock;
    private Integer price;
}
