package bookShelf.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Favorite {
    private String id;
    @DBRef
    private User userId;
    @DBRef
    private Book bookId;
}
