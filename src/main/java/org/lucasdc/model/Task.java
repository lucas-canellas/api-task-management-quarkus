package org.lucasdc.model;

import jakarta.persistence.*;
import lombok.Data;
import org.lucasdc.dto.TaskRequest;

import java.time.LocalDateTime;

@Entity
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime date;
    private Priority priority;
    private Status status;
    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public static Task fromTaskRequest(TaskRequest taskRequest, Category category, User user) {
        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setDate(taskRequest.getDate());
        task.setPriority(taskRequest.getPriority());
        task.setStatus(taskRequest.getStatus());
        task.setCategory(category);
        task.setUser(user);
        return task;
    }

}
