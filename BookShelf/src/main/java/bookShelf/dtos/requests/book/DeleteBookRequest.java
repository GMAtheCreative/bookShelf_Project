package bookShelf.dtos.requests.book;

import lombok.Data;

@Data
public class DeleteBookRequest {
    private int bookId;
    private String title;
    private String author;
    private String userId;
}
