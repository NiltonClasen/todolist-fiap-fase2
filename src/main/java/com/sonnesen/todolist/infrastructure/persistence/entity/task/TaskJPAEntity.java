package com.sonnesen.todolist.infrastructure.persistence.entity.task;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.sonnesen.todolist.domain.task.entity.Task;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
@Builder
public class TaskJPAEntity {

    public static final String SEQUENCE_NAME = "tasks_sequence";
    @Id
    private Long id;

    private String title;

    private String description;

    private boolean completed;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant deletedAt;

    public void markAsCompleted() {
        this.completed = true;
    }

    public void reopen() {
        this.completed = false;
    }

    public boolean isDeleted() {
        return this.deletedAt != null;
    }

    public static TaskJPAEntity of(Task task) {
        return new TaskJPAEntity(task.getId(), task.getTitle(), task.getDescription(), task.isCompleted(),
                task.getCreatedAt(), task.getUpdatedAt(), task.getDeletedAt());
    }

    public Task toTask() {
        return Task.with(this.id, this.title, this.description, this.completed, this.createdAt, this.updatedAt,
                this.deletedAt);
    }
}
