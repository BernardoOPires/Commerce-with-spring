package com.devsuperior.DsCommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.DsCommerce.DTO.ProductDTO;
import com.devsuperior.DsCommerce.entities.Product;
import com.devsuperior.DsCommerce.repositories.ProductRepository;



@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    //modelo para estudos
   @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        Optional<Product> result = repo.findById(id);
        Product product = result.get();
        ProductDTO dto = new ProductDTO(product);
        return dto;  
    }

    //Modelo compacto e mais comum
    //  @Transactional(readOnly = true)
    // public ProductDTO findById(Long id){
    //     Product product = repo.findById(id).get();
    //     return new ProductDTO(product);  
    // }
}
