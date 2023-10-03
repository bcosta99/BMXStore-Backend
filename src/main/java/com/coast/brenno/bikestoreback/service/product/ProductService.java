package com.coast.brenno.bikestoreback.service.product;

import com.coast.brenno.bikestoreback.dto.request.product.CreateProductRequest;
import com.coast.brenno.bikestoreback.model.Product;
import com.coast.brenno.bikestoreback.repository.ProductRepository;
import com.coast.brenno.bikestoreback.service.file.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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

    public ResponseEntity<String> uploadFiles(MultipartFile[] files) {

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