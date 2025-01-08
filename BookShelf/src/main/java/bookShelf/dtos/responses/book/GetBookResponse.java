package bookShelf.dtos.responses.book;

import bookShelf.data.models.Book;
import lombok.Data;

import java.util.List;

@Data
public class GetBookResponse {
    List<Book> books;
    private String message;
}
