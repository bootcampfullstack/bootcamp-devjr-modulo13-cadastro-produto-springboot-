package com.abutua.productbackend.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.abutua.productbackend.dao.ProductDAO;
import com.abutua.productbackend.dao.ProductSaveDAO;
import com.abutua.productbackend.models.Category;
import com.abutua.productbackend.models.Product;
import com.abutua.productbackend.repositories.CategoryRepository;
import com.abutua.productbackend.repositories.ProductRepository;
import com.abutua.productbackend.services.exceptions.DatabaseException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductDAO getById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found."));

        return product.toDAO();
    }

    public List<ProductDAO> getAll() {
        return productRepository.findAll()
                .stream()
                .map(p -> p.toDAO())
                .collect(Collectors.toList());
    }

    public ProductDAO save(ProductSaveDAO productSaveDAO) {
        try {
            Product saveProduct = productSaveDAO.toEntity();
            Product product = productRepository.save(saveProduct);
            return product.toDAO();
        } 
        catch (DataIntegrityViolationException e) {
            throw new EntityNotFoundException("Category not found");
        }
    }

    public void deleteById(long id) {
        try {
            productRepository.deleteById(id);
        }
        catch(EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Product not found"); 
        }

       
    }

    public void update(long id, ProductSaveDAO productSaveDAO) {
        try {
            Product productEntity = productRepository.getReferenceById(id);
            Product productUpdate = productSaveDAO.toEntity();

            productEntity.setDescription(productUpdate.getDescription());
            productEntity.setName(productUpdate.getName());
            productEntity.setPrice(productUpdate.getPrice());
            productEntity.setNewProduct(productUpdate.isNewProduct());
            productEntity.setPromotion(productUpdate.isPromotion());
            productEntity.setCategory(productUpdate.getCategory());

            productRepository.save(productEntity);

        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Product not found");
        } catch (DataIntegrityViolationException e) {
            throw new EntityNotFoundException("Category not found");
        }
    }

}
