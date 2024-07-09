package org.lucasdc.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.lucasdc.model.Task;
import org.lucasdc.repository.TaskRepository;

@ApplicationScoped
public class TaskService {

    @Inject
    TaskRepository taskRepository;

    public Task save(Task task) {

        return null;
    }
}
