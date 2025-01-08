package bookShelf.services;

import bookShelf.dtos.requests.book.*;
import bookShelf.dtos.responses.book.*;


public interface BookServices {

    AddBookResponse addBook(AddBookRequest addBookRequest);

    GetBookResponse getBookByTitle(GetBookRequest getBookRequest);

    UpdateBookResponse updateBook(UpdateBookRequest updateBookRequest);

    GetBookResponse getAllBooks(GetBookRequest request);

    DeleteBookResponse deleteBook(DeleteBookRequest deleteBookRequest);

    GetBookResponse getBookByAuthor(GetBookRequest getBookRequest);

    ReadBookResponse readBook (ReadBookRequest readBook);
}
