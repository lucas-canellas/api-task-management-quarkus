package org.lucasdc.dto;

import jakarta.persistence.*;
import lombok.Data;
import org.lucasdc.model.*;

import java.time.LocalDateTime;

@Data
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private Priority priority;
    private Status status;

    public static TaskResponse fromTask(Task task) {
        TaskResponse response = new TaskResponse();
        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setPriority(task.getPriority());
        response.setStatus(task.getStatus());
        return response;
    }
}
