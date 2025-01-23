package bookShelf.dtos.requests.book;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

//@Data
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AddBookRequest {
    private String title;
    private String author;
    private String description;
    private MultipartFile pdf;
    private String userId;
}
