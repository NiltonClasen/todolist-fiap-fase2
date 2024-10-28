package com.sonnesen.todolist.infrastructure.persistence.mongo;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@AllArgsConstructor
public class DatabaseSequenceGenerationService {
    private final MongoOperations mongoOperations;

    public Long generateSequence(final String sequenceName) {
        final DatabaseSequence counter = mongoOperations.findAndModify(
                query(where("_id").is(sequenceName)),
                new Update().inc("value", 1),
                options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return counter != null ? counter.getValue() : 1;
    }
    ////////////////////////////////PAREI EM 30MINUTOSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS

}
