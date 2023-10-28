package com.personal.amacloneserver.model;

import com.personal.amacloneserver.dto.ProductCreateDto;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
    private String productImageLinks;
    private Double price;
    private String color;

    public Product(ProductCreateDto productCreateDto, Category category) {
        this.name = productCreateDto.getProductName();
        this.category = category;
        this.productImageLinks = productCreateDto.getProductImageLinks();
        this.price = productCreateDto.getPrice();
        this.color = productCreateDto.getColor();
    }
}
