package com.personal.amacloneserver.service;

import com.personal.amacloneserver.exception.CategoryNotFoundException;
import com.personal.amacloneserver.model.Category;
import com.personal.amacloneserver.model.Product;
import com.personal.amacloneserver.repository.CategoryRepository;
import com.personal.amacloneserver.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final CustomerService customerService;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public AdminService(CustomerService customerService, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.customerService = customerService;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public Category createCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }
    public Category getCategory(Long id) {
        return this.categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
    }

    public Product addProduct(Product product) {
        return this.productRepository.save(product);
    }
}
