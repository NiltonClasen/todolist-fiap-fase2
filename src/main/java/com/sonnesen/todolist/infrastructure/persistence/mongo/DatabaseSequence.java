package com.sonnesen.todolist.infrastructure.persistence.mongo;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "database_sequences")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
@Builder
public class DatabaseSequence {

    @Id
    private String id;

    private Long value;
}
