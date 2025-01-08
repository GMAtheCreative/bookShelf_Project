package bookShelf.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Books")
@Data
public class Book {
    @Id
    private String id;
    private String title;
    private String author;
    private String description;
    private String isbn;
    private byte[] image;
    private byte[] document;
    private String userId;
}
