package com.coast.brenno.bikestoreback.dto.request.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddToCartReq {
    private @NotNull Long productId;
    private @NotNull Integer quantity;

    @Override
    public String toString() {
        return "CartDto{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                ",";
    }
}