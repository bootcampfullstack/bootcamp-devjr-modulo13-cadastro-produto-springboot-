package com.abutua.productbackend.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.abutua.productbackend.dao.CategoryDAO;
import com.abutua.productbackend.dao.CategorySaveDAO;
import com.abutua.productbackend.models.Category;
import com.abutua.productbackend.repositories.CategoryRepository;
import com.abutua.productbackend.services.exceptions.DatabaseException;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDAO getById(int id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        return category.toDAO();
    }

    public List<CategoryDAO> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(c -> c.toDAO())
                .collect(Collectors.toList());
    }

    public CategoryDAO save(CategorySaveDAO categorySaveDAO) {
        Category category = categoryRepository.save(categorySaveDAO.toEntity());
        return category.toDAO();
    }

    public void deleteById(int id) {
        try {
            categoryRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Constraint violation, category can't delete");
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Category not found");
        } 
    }

    public void update(int id, CategorySaveDAO categorySaveDAO) {
        try {
            Category category = categoryRepository.getReferenceById(id);
            category.setName(categorySaveDAO.getName());
            categoryRepository.save(category);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Entity not found");
        } 
    }

}
