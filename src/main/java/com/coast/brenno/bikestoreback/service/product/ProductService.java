package com.coast.brenno.bikestoreback.service.product;

import com.coast.brenno.bikestoreback.dto.request.product.CreateProductRequest;
import com.coast.brenno.bikestoreback.model.Product;
import com.coast.brenno.bikestoreback.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ArrayList<Product> getAllProducts() {
        return (ArrayList<Product>) productRepository.findAll();
    }

    public Product getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Product found with id:" + id));

        return product;
    }

    public List<Product> getAvailableProducts() {
        List<Product> products = productRepository.getAvailableProducts();
        return products;
    }

    public List<Product> getCategoryProducts(String category) {
        List<Product> productsCat = productRepository.getByCategory(category);
        return productsCat;
    }

    public List<Product> searchProducts(String request) {
        List<Product> products = productRepository.searchProductByName(request);
        if (products.isEmpty()) {
            throw new RuntimeException();
        }
        return products;
    }

    public Product createProduct(CreateProductRequest request) {
        Product newProduct = new Product();

        if (request.getName().isEmpty()) {
            throw new RuntimeException("Name cannot be null");
        }

        if (request.getStock() < 0) {
            throw new RuntimeException("Stock must be greater than 0");
        }

        if (request.getPrice() < 0) {
            throw new RuntimeException("Stock must be greater than 0");
        }

        newProduct.setCategory(request.getCategory());
        newProduct.setName(request.getName());
        newProduct.setDescription(request.getDescription());
        newProduct.setColor(request.getColor());
        newProduct.setStock(request.getStock());
        newProduct.setPrice(request.getPrice());

        return productRepository.save(newProduct);
    }

    public Product putProduct(Long id, CreateProductRequest request) {
        Product updateProduct = new Product();

        updateProduct.setId(id);

        if (!(request.getCategory()).isEmpty()) {
            updateProduct.setCategory(request.getCategory());
        }
        if (!(request.getName()).isEmpty()) {
            updateProduct.setName(request.getName());
        }
        if (!(request.getDescription()).isEmpty()) {
            updateProduct.setDescription(request.getDescription());
        }
        if (!(request.getColor()).isEmpty()) {
            updateProduct.setColor(request.getColor());
        }
        if (request.getStock() != null) {
            updateProduct.setStock(request.getStock());
        }
        if (request.getPrice() != null) {
            updateProduct.setPrice(request.getPrice());
        }

        return productRepository.save(updateProduct);
    }

    public ResponseEntity<HttpStatus> deleteProduct(Long id) {
        productRepository.deleteById(id);
        try {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}