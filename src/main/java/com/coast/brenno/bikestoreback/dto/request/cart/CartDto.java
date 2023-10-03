package com.coast.brenno.bikestoreback.dto.request.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CartDto {

    private List<CartItemDto> cartItems;
//    private double totalCost;
}