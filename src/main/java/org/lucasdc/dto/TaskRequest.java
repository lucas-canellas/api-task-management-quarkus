package org.lucasdc.dto;

import lombok.Data;
import org.lucasdc.model.Priority;
import org.lucasdc.model.Status;

import java.time.LocalDateTime;

@Data
public class TaskRequest {
    private String title;
    private String description;
    private LocalDateTime date;
    private Priority priority;
    private Status status;
    private Long categoryId;
    private Long userId;
}
