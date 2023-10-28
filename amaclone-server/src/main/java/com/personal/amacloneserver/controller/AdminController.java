package com.personal.amacloneserver.controller;

import com.personal.amacloneserver.dto.ProductCreateDto;
import com.personal.amacloneserver.model.Category;
import com.personal.amacloneserver.model.Product;
import com.personal.amacloneserver.service.AdminService;
import com.personal.amacloneserver.service.CustomerService;
import com.personal.amacloneserver.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;
    private final CustomerService customerService;
    private final FileStorageService fileStorageService;

    @Autowired
    public AdminController(AdminService adminService, CustomerService customerService, FileStorageService fileStorageService) {
        this.adminService = adminService;
        this.customerService = customerService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.adminService.createCategory(category));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(this.adminService.getAllCategories());
    }

    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestBody ProductCreateDto productCreateDto, @RequestParam("file")MultipartFile multipartFile) {
        Category category = this.adminService.getCategory(productCreateDto.getCategoryId());
        Product product = new Product(productCreateDto, category);
        this.fileStorageService.storeFile(multipartFile);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.adminService.addProduct(product));
    }

}
