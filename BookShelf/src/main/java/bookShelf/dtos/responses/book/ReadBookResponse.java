package bookShelf.dtos.responses.book;

import lombok.Data;

@Data
public class ReadBookResponse {
private String title;
private String author;
private byte[] document;
}
