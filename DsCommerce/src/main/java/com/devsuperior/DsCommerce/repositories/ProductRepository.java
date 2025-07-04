package com.devsuperior.DsCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.DsCommerce.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
