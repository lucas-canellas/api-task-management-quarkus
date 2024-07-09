package org.lucasdc.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.lucasdc.model.Category;
import org.lucasdc.model.Task;

@ApplicationScoped
public class CategoryRepository implements PanacheRepository<Category> {

    public Category findByName(String categoryName) {
        return find("name", categoryName).firstResult();
    }

}
