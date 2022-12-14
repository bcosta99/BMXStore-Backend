package com.coast.brenno.bikestoreback.dto.request.cart;

import com.coast.brenno.bikestoreback.model.Cart;
import com.coast.brenno.bikestoreback.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    private Integer id;
    private @NotNull Integer quantity;
    private @NotNull Product product;

    public CartItemDto(Cart cart) {
        this.setId(cart.getId());
        this.setQuantity(cart.getQuantity());
        this.setProduct(cart.getProduct());
    }

    @Override
    public String toString() {
        return "CartDto{" + "id=" + id + ", quantity=" + quantity + ", productName=" + product.getName() + '}';
    }
}