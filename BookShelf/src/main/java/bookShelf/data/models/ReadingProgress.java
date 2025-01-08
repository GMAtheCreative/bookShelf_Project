package bookShelf.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
@Document
@Data
public class ReadingProgress {
    private String id;
    @DBRef
    private User userId;
    @DBRef
    private Book bookId;

    private int currentPage;
    private LocalDate lastOpened = LocalDate.now();
}
