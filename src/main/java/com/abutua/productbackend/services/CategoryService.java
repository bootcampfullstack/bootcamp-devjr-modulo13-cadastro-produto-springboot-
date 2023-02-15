package com.abutua.productbackend.services;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.abutua.productbackend.dao.CategoryDAO;
import com.abutua.productbackend.dao.CategorySaveDAO;
import com.abutua.productbackend.models.Category;
import com.abutua.productbackend.repositories.CategoryRepository;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public CategoryDAO getDAOById(int id) {
        return getById(id).toDAO();
    }
  
    public Category getById(int id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        return category;
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
        Category category = getById(id);
        categoryRepository.delete(category);
    }

    public void update(int id, CategorySaveDAO categorySaveDAO) {
        Category category = getById(id);
        category.setName(categorySaveDAO.getName());
        categoryRepository.save(category);
    }

}
