package org.lucasdc.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.lucasdc.model.Task;

import java.util.List;

@ApplicationScoped
public class TaskRepository implements PanacheRepository<Task> {
    public List<Task> findByEmail(String email) {
        return find("user.email", email).list();
    }

}
