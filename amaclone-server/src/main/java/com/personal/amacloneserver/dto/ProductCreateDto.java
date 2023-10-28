package com.personal.amacloneserver.dto;

import lombok.Data;

@Data
public class ProductCreateDto {
    private String productName;
    private Long categoryId;
    private String productImageLinks;
    private Double price;
    private String color;
}
