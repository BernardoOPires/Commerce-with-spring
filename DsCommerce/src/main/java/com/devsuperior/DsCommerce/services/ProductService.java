package com.devsuperior.DsCommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    //retorna tudo
    // @Transactional(readOnly = true)
    // public List<ProductDTO> findAll(){
    //     List<Product> result = repo.findAll();
    //     return result.stream().map(x -> new ProductDTO(x)).toList();
    // }

    //paginado -- basta adicionar ?size="num de itens", http://localhost:8080/products?size=12
    //http://localhost:8080/products?size=12&page=1  o &page faz ele começar na pagina definida
    //http://localhost:8080/products?size=12&page=0&sort=name, esse sort=name vai ordenar pela variavel name
    //http://localhost:8080/products?size=12&page=0&sort=name,desc, faz ser decrescente
    //use para definir a pagina e tamanho que o usuario vai ver no front
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable){
        Page<Product> result = repo.findAll(pageable);
        return result.map(x -> new ProductDTO(x));
    }
}
