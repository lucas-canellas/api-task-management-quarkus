package org.lucasdc.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.lucasdc.dto.TaskRequest;
import org.lucasdc.exception.BusinessException;
import org.lucasdc.model.Category;
import org.lucasdc.model.Task;
import org.lucasdc.model.User;
import org.lucasdc.repository.CategoryRepository;
import org.lucasdc.repository.TaskRepository;
import org.lucasdc.repository.UserRepository;

import java.util.List;

@ApplicationScoped
public class TaskService {

    @Inject
    TaskRepository taskRepository;

    @Inject
    CategoryRepository categoryRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    JsonWebToken jwtWebToken;

    @Transactional
    public Task createTask(TaskRequest taskRequest) {
        Category category = categoryRepository.findById(taskRequest.getCategoryId());
        if (category == null) {
            throw new BusinessException("Categoria não encontrada");
        }

        String email = jwtWebToken.getClaim("email").toString();

        User user = userRepository.findByEmail(email);

        Task task = Task.fromTaskRequest(taskRequest, category, user);
        taskRepository.persist(task);
        return task;
    }

    public List<Task> getTasksByUserEmail(String email) {
        return taskRepository.findByEmail(email);
    }
}
