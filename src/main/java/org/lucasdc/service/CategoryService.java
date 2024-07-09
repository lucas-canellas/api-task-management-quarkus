package org.lucasdc.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.lucasdc.exception.BusinessException;
import org.lucasdc.model.Category;
import org.lucasdc.repository.CategoryRepository;

@ApplicationScoped
public class CategoryService {

    @Inject
    CategoryRepository categoryRepository;


    @Transactional
    public Category save(Category category) {

        var categoryFound = categoryRepository.findByName(category.getName());

        if(categoryFound != null) {
            throw new BusinessException("Já existe uma categoria com esse nome");
        }

        Category newCategory = new Category();
        newCategory.setName(category.getName());
        newCategory.setDescription(category.getDescription());

        categoryRepository.persist(newCategory);

        return newCategory;
    }
}
