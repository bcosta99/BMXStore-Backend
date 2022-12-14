package com.coast.brenno.bikestoreback.repository;

import com.coast.brenno.bikestoreback.model.Cart;
import com.coast.brenno.bikestoreback.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByUserOrderByCreatedDateDesc(User user);

    List<Cart> deleteByUser(User user);
}