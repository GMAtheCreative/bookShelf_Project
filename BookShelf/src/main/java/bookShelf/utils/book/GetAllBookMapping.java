package bookShelf.utils.book;

import bookShelf.data.models.Book;
import bookShelf.dtos.responses.book.GetAllBookResponse;
import bookShelf.dtos.responses.book.GetBookResponse;

import java.util.ArrayList;
import java.util.List;

public class GetAllBookMapping {

    public static GetAllBookResponse getGetBookResponse(List<Book> books) {
        List<GetBookResponse> getBookResponses = new ArrayList<>();
        GetAllBookResponse bookResponse = new GetAllBookResponse();

        for (Book book : books) {
            GetBookResponse getBookResponse = new GetBookResponse();
            getBookResponse.setImages(book.getImage());
            getBookResponse.setAuthor(book.getAuthor());
            getBookResponse.setTitle(book.getTitle());
            getBookResponses.add(getBookResponse);
        }

        bookResponse.setGetBookResponses(getBookResponses);
        bookResponse.setMessage(getBookResponses.size() + " Book(s) found");
        return bookResponse;
    }
}
