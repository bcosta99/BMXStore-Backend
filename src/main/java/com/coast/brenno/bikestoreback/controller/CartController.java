package com.coast.brenno.bikestoreback.controller;

import com.coast.brenno.bikestoreback.dto.request.cart.AddToCartReq;
import com.coast.brenno.bikestoreback.dto.request.cart.CartDto;
import com.coast.brenno.bikestoreback.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody AddToCartReq req) {
        cartService.addItemToCart(req);
        return ResponseEntity.ok("Item added to Cart succesfully!");
    }

    @GetMapping(path = {"", "/"})
    public ResponseEntity<CartDto> getCartItems() {
        CartDto cartDto = cartService.listCartItems();
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity deleteCartItem(@PathVariable("cartItemId") Integer id) {
        cartService.deleteCartItem(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}