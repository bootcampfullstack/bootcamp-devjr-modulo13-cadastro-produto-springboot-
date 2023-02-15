package com.abutua.productbackend.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.abutua.productbackend.dao.CategoryDAO;
import com.abutua.productbackend.dao.CategorySaveDAO;
import com.abutua.productbackend.services.CategoryService;

@RestController
@CrossOrigin
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDAO> save(@Validated @RequestBody CategorySaveDAO categorySaveDAO) {
        CategoryDAO categoryDAO = categoryService.save(categorySaveDAO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoryDAO.getId())
                .toUri();
 
        return ResponseEntity.created(location).body(categoryDAO);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryDAO> getCategory(@PathVariable int id) {
        CategoryDAO categoryDAO = categoryService.getDAOById(id);
        return ResponseEntity.ok(categoryDAO);
    }

    @GetMapping  
    public ResponseEntity<List<CategoryDAO>> getCategories() { 
        return ResponseEntity.ok(categoryService.getAll());
    }

    @DeleteMapping("{id}")    
    public ResponseEntity<Void> removeCategory(@PathVariable int id) {
        categoryService.deleteById(id);                
        return ResponseEntity.noContent().build(); 
    }
   
    @PutMapping("{id}")    
    public ResponseEntity<Void> updateCategory(@PathVariable int id,@Validated @RequestBody CategorySaveDAO categorySaveDAO) {
        categoryService.update(id, categorySaveDAO);
        return ResponseEntity.ok().build(); 
    }
}
