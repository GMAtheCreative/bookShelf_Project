package bookShelf.dtos.requests.book;

import lombok.Data;

@Data
public class GetBookRequest {
    private String title;
    private String author;
    private String userId;
}
