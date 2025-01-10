package bookShelf.dtos.responses.book;

import lombok.Data;

import java.util.List;

@Data
public class GetAllBookResponse {
    private String message;
    private List<GetBookResponse> getBookResponses;
}
