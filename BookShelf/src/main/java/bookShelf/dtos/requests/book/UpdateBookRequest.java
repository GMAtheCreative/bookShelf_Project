package bookShelf.dtos.requests.book;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateBookRequest {
    private String originalTitle;
    private String originalAuthor;
    private String title;
    private String author;
    private String description;
    private MultipartFile image;
    private MultipartFile pdf;
    private String userId;
}
