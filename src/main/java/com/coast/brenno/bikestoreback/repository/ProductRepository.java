package com.coast.brenno.bikestoreback.repository;

import com.coast.brenno.bikestoreback.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT c FROM Product c WHERE c.category = ?1")
    List<Product> getByCategory(String category);

    @Query("SELECT p FROM Product p WHERE p.stock > 0")
    List<Product> getAvailableProducts();

    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
    List<Product> searchProductByName(String name);
}