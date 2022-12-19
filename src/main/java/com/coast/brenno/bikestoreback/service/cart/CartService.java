package com.coast.brenno.bikestoreback.service.cart;

import com.coast.brenno.bikestoreback.dto.request.cart.AddToCartReq;
import com.coast.brenno.bikestoreback.dto.request.cart.CartDto;
import com.coast.brenno.bikestoreback.dto.request.cart.CartItemDto;
import com.coast.brenno.bikestoreback.model.Cart;
import com.coast.brenno.bikestoreback.model.Product;
import com.coast.brenno.bikestoreback.model.User;
import com.coast.brenno.bikestoreback.model.UserDetailsImpl;
import com.coast.brenno.bikestoreback.repository.CartRepository;
import com.coast.brenno.bikestoreback.service.product.ProductService;
import com.coast.brenno.bikestoreback.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    private final ProductService productService;

    private final UserService userService;

    public static CartItemDto getDtoFromCart(Cart cart) {
        return new CartItemDto(cart);
    }

    public void addItemToCart(@NotNull AddToCartReq req) {
        Product product = productService.getProductById(req.getProductId());

        User user = getCurrentUser();

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProduct(product);
        cart.setCreatedDate(new Date());
        cart.setQuantity(req.getQuantity());

        cartRepository.save(cart);
    }

    private User getCurrentUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        return userService.getUserByUsername(username);
    }

    public CartDto listCartItems() {
        User user = getCurrentUser();
        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<CartItemDto> cartItems = new ArrayList<>();
        for (Cart cart : cartList) {
            CartItemDto cartItemDto = getDtoFromCart(cart);
            cartItems.add(cartItemDto);
        }
//        double totalCost = 0;
//        for (CartItemDto cartItemDto :cartItems){
//            totalCost += (cartItemDto.getProduct().getPrice()* cartItemDto.getQuantity());
//        }
        return new CartDto(cartItems);
    }

    //    public void updateCartItem(AddToCartDto cartDto, User user, Product product) {
//        Cart cart = cartRepository.getOne(Math.toIntExact(cartDto.getProductId()));
//        cart.setQuantity(cartDto.getQuantity());
//        cart.setCreatedDate(new Date());
//        cartRepository.save(cart);
//    }
//
    public void deleteCartItem(Integer id) {
        cartRepository.deleteById(id);
    }
//
//    public void deleteCartItems(int userId) {
//        cartRepository.deleteAll();
//    }
//
//
//    public void deleteUserCartItems(User user) {
//        cartRepository.deleteByUser(user);
//    }
}