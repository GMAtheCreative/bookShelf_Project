package bookShelf.dtos.requests.book;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AddBookRequest {
    private String title;
    private String author;
    private String description;
    private String isbn;
    private MultipartFile pdf;
    private String userId;
}
