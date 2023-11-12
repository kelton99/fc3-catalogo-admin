package com.fc.kelton.admin.catalogo.application;

import com.fc.kelton.admin.catalogo.domain.category.Category;

public class UseCase {
    public Category execute(String name, String description, boolean active) {
        return Category.newCategory(name, description, active);
    }
}