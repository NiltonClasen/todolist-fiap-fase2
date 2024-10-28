package com.sonnesen.todolist.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sonnesen.todolist.infrastructure.persistence.entity.task.TaskJPAEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskJPARepository extends MongoRepository<TaskJPAEntity, Long> {

}
