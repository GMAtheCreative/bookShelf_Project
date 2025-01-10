package bookShelf.services;

import bookShelf.dtos.requests.book.*;
import bookShelf.dtos.responses.book.*;


public interface BookServices {

    AddBookResponse addBook(AddBookRequest addBookRequest);

    GetAllBookResponse getBookByTitle(GetBookRequest getBookRequest);

    UpdateBookResponse updateBook(UpdateBookRequest updateBookRequest);

    GetAllBookResponse getAllBooks(GetBookRequest request);

    DeleteBookResponse deleteBook(DeleteBookRequest deleteBookRequest);

    GetAllBookResponse getBookByAuthor(GetBookRequest getBookRequest);

    ReadBookResponse readBook (ReadBookRequest readBook);
}
