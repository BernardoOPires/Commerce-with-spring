package com.devsuperior.DsCommerce.DTO;

import com.devsuperior.DsCommerce.entities.Product;

public class ProductDTO {
   
    private long id;
    private String name;
    private String description;
    private double price;
    private String imgUrl;

    public ProductDTO(long id, String name, String description, double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ProductDTO(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.imgUrl = entity.getDescription();
    }

    public ProductDTO(){}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

}
