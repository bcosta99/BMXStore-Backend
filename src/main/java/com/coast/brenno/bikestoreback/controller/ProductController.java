package com.coast.brenno.bikestoreback.controller;

import com.coast.brenno.bikestoreback.dto.request.product.CreateProductRequest;
import com.coast.brenno.bikestoreback.model.Product;
import com.coast.brenno.bikestoreback.service.file.FileUtil;
import com.coast.brenno.bikestoreback.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    /**
     * Get All Products
     */
    @GetMapping(path = {"", "/"}, produces = "application/json")
    public ResponseEntity getAllProducts() {
        List<Product> allItems = service.getAllProducts();
        return ResponseEntity.ok(allItems);
    }

    /**
     * Get A Specific Product to show details
     */
    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity getProductByID(@PathVariable Long id) {
        Product oneItem = service.getProductById(id);
        return ResponseEntity.ok(oneItem);
    }


    /**
     * Get All Products of the same category
     */
    @GetMapping(path = "/category/{category}", produces = "application/json")
    public ResponseEntity getByCategory(@PathVariable String category) {
        List<Product> categoryItems = service.getCategoryProducts(category);
        return ResponseEntity.ok(categoryItems);
    }

    /**
     * Search Products By Name On Real Time
     */
    @GetMapping(path = "/search", produces = "application/json")
    public ResponseEntity searchByName(@RequestParam(name = "name") String request) {
        List<Product> likeItems = service.searchProducts(request);
        return ResponseEntity.ok(likeItems);
    }

    /**
     * Create A Product
     */
    @PostMapping(path = "/", produces = "application/json")
    public ResponseEntity postProduct(@RequestBody CreateProductRequest request) {
        Product newItem = service.createProduct(request);
        return ResponseEntity.ok(newItem);
    }

    /**
     * Update A Product
     */
    @PutMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity updateProduct(@PathVariable("id") Long id, @RequestBody CreateProductRequest request) {
        Product editItem = service.putProduct(id, request);
        return ResponseEntity.ok(editItem);
    }

    /**
     * Delete A Product (CHANGE JUST A FIELD)
     */
    @DeleteMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity deleteProduct(@PathVariable("id") Long id) {
        ResponseEntity<HttpStatus> deletItem = service.deleteProduct(id);
        return ResponseEntity.ok(deletItem);
    }

    /**
     * Method to upload multiple files
     *
     * @param files
     * @return FileResponse
     */
    @PostMapping("/upload")
    public ResponseEntity uploadFiles(@RequestParam("files") MultipartFile[] files) {
        try {
            createDirIfNotExist();

            List<String> fileNames = new ArrayList<>();

            // read and write the file to the local folder
            Arrays.asList(files).stream().forEach(file -> {
                byte[] bytes = new byte[0];
                try {
                    bytes = file.getBytes();
                    Files.write(Paths.get(FileUtil.folderPath + file.getOriginalFilename()), bytes);
                    fileNames.add(file.getOriginalFilename());
                } catch (IOException e) {

                }
            });

            return ResponseEntity.status(HttpStatus.OK)
                    .body("Files uploaded successfully: " + fileNames);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body("Exception to upload files!");
        }
    }

    /**
     * Create directory to save files, if not exist
     */
    private void createDirIfNotExist() {
        //create directory to save the files
        File directory = new File(FileUtil.folderPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    /**
     * Method to get the list of files from the file storage folder.
     *
     * @return file
     */
    @GetMapping("/files")
    public ResponseEntity<String[]> getListFiles() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new File(FileUtil.folderPath).list());
    }
}