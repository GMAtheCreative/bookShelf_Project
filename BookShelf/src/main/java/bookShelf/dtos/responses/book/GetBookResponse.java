package bookShelf.dtos.responses.book;

import bookShelf.data.models.Book;
import lombok.Data;

import java.util.List;

@Data
public class GetBookResponse {
    private byte[] images;
    private String title;
    private String author;
    private String description;
}
