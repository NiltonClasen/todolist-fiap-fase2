package com.sonnesen.todolist.infrastructure.config;

import com.sonnesen.todolist.infrastructure.event.TaskEventListener;
import com.sonnesen.todolist.infrastructure.persistence.mongo.DatabaseSequenceGenerationService;
import com.sonnesen.todolist.infrastructure.persistence.mongo.TaskEntityListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;

@Configuration
public class MongoDatabaseConfig {

    @Bean
    DatabaseSequenceGenerationService databaseSequenceGenerationService(final MongoOperations mongoOperations) {
        return new DatabaseSequenceGenerationService(mongoOperations);
    }

    @Bean
    TaskEntityListener taskEntityListener(final DatabaseSequenceGenerationService databaseSequenceGenerationService) {
        return new TaskEntityListener(databaseSequenceGenerationService);
    }
}
