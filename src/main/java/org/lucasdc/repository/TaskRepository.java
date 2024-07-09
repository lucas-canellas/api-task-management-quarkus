package org.lucasdc.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.lucasdc.model.Task;

@ApplicationScoped
public class TaskRepository implements PanacheRepository<Task> {

}
