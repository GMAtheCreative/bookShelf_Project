package bookShelf.dtos.requests.book;

import lombok.Data;

@Data
public class ReadBookRequest {
    private String userId;
    private String title;
    private String author;
}
