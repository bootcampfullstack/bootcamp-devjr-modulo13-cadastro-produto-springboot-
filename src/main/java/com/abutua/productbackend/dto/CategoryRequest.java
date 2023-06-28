package com.abutua.productbackend.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.abutua.productbackend.models.Category;

public class CategoryRequest {
    
    @NotBlank(message = "Name can not be blank")
    @Size(min=3, max = 255, message = "Name length min=3 and max=255")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
   
    public Category toEntity(){
        return new Category(name);
    }
}
