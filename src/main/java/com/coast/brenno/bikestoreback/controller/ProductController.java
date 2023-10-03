package com.coast.brenno.bikestoreback.controller;

import com.coast.brenno.bikestoreback.dto.request.product.CreateProductRequest;
import com.coast.brenno.bikestoreback.model.Product;
import com.coast.brenno.bikestoreback.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private final ProductService productService;

    /**
     * Get All Products
     */
    @GetMapping(path = {"", "/"}, produces = "application/json")
    public ResponseEntity getAllProducts() {
        List<Product> allItems = productService.getAllProducts();
        return ResponseEntity.ok(allItems);
    }

    /**
     * Get a Specific Product to show details
     */
    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity getProductByID(@PathVariable Long id) {
        Product oneItem = productService.getProductById(id);
        return ResponseEntity.ok(oneItem);
    }

    /**
     * Get All Products of the same category
     */
    @GetMapping(path = "/category/{category}", produces = "application/json")
    public ResponseEntity getByCategory(@PathVariable String category) {
        List<Product> categoryItems = productService.getCategoryProducts(category);
        return ResponseEntity.ok(categoryItems);
    }

    /**
     * Search Products By Name On Real Time
     */
    @GetMapping(path = "/search", produces = "application/json")
    public ResponseEntity searchByName(@RequestParam(name = "name") String request) {
        List<Product> likeItems = productService.searchProducts(request);
        return ResponseEntity.ok(likeItems);
    }

    /**
     * Create A Product
     */
    @PostMapping(path = "/", produces = "application/json")
    public ResponseEntity postProduct(@RequestBody CreateProductRequest request) {
        Product newItem = productService.createProduct(request);
        return ResponseEntity.ok(newItem);
    }

    /**
     * Update A Product
     */
    @PutMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity updateProduct(@PathVariable("id") Long id, @RequestBody CreateProductRequest request) {
        Product editItem = productService.putProduct(id, request);
        return ResponseEntity.ok(editItem);
    }

    /**
     * Delete A Product (CHANGE JUST A FIELD)
     */
    @DeleteMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity deleteProduct(@PathVariable("id") Long id) {
        ResponseEntity<HttpStatus> deletItem = productService.deleteProduct(id);
        return ResponseEntity.ok(deletItem);
    }

    /**
     * Method to upload multiple files
     *
     * @param files
     * @return FileResponse
     */
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFiles(@RequestParam("files") MultipartFile[] files) {
        return productService.uploadFiles(files);
    }
}