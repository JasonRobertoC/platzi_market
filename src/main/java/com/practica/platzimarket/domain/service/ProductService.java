package com.practica.platzimarket.domain.service;

import com.practica.platzimarket.domain.Product;
import com.practica.platzimarket.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.getAll();
    }

    public Optional<Product> getProduct(int productId){
        return productRepository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int categoryId){
        return productRepository.getByCategory(categoryId);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public boolean delete(int productId) {
        return getProduct(productId).map(product -> {
            productRepository.delete(productId);
            return true;
        }).orElse(false);
    }

//    public boolean delete(int productId) {
//        if (productRepository.getProduct(productId).isEmpty()) {
//            return false;
//        }
//        productRepository.delete(productId);
//        return true;
//    }

//    public boolean delete(int productId){
//        try {
//            productRepository.delete(productId);
//            return true;
//        } catch (EmptyResultDataAccessException e) {
//            return false;
//        }
//    }

//    public boolean delete(int productId){
//        if(getProduct(productId).isPresent()){
//            productRepository.delete(productId);
//            return true;
//        } else {
//            return false;
//        }
//    }

}
