package com.devsuperior.DsCommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.DsCommerce.DTO.ProductDTO;
import com.devsuperior.DsCommerce.entities.Product;
import com.devsuperior.DsCommerce.repositories.ProductRepository;
import com.devsuperior.DsCommerce.services.Exceptions.DatabaseException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    //modelo para estudos
//    @Transactional(readOnly = true)
//     public ProductDTO findById(Long id){
//         Optional<Product> result = repo.findById(id);
//         Product product = result.get();
//         ProductDTO dto = new ProductDTO(product);
//         return dto;  
//     }
    //Modelo compacto e mais comum
    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = repo.findById(id).orElseThrow(
                () -> new DatabaseException("Item não encontrado"));
        return new ProductDTO(product);
    }

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
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> result = repo.findAll(pageable);
        return result.map(x -> new ProductDTO(x));
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        copyDtoToEntity(dto, entity);

        entity = repo.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product entity = repo.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repo.save(entity);
            return new ProductDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new DatabaseException("recurso não encontrado");
        }
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgURL(dto.getImgUrl());
    }

    // @Transactional
    // public void delete(Long id) {
    //     repo.deleteById(id);
    // }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new DatabaseException("Recurso não encontrado");
        }
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial, produto  esta sendo referenciado em outra tabela");
        }
    }
}
