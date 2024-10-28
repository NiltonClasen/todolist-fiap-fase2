package com.sonnesen.todolist.infrastructure.persistence.mongo;

import com.sonnesen.todolist.infrastructure.persistence.entity.task.TaskJPAEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.lang.NonNull;

import java.time.Instant;
import java.util.Optional;

@AllArgsConstructor
public class TaskEntityListener extends AbstractMongoEventListener<TaskJPAEntity> {

        private final DatabaseSequenceGenerationService databaseSequenceGenerationService;

        @Override
        public void onBeforeConvert(@NonNull BeforeConvertEvent<TaskJPAEntity> event) {
            final Long taskId = Optional.ofNullable(event.getSource().getId())
                    .orElse(0L);

            if (taskId == 0) {
                event.getSource().setId(databaseSequenceGenerationService.generateSequence(TaskJPAEntity.SEQUENCE_NAME));
            }

            final Instant now = Instant.now();

            if(event.getSource().getCreatedAt() == null) {
                event.getSource().setCreatedAt(now);
            }

            event.getSource().setUpdatedAt(now);
        }

}
