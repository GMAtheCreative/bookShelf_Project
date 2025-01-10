package bookShelf.services;
import bookShelf.data.models.Book;
import bookShelf.data.repositories.BookRepository;
import bookShelf.dtos.requests.book.*;
import bookShelf.dtos.responses.book.*;
import bookShelf.exception.bookException.BookNotFound;
import bookShelf.exception.bookException.MediaStorageException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static bookShelf.utils.book.BookMapping.*;
import static bookShelf.utils.book.BookValidation.extractImageFromFirstPage;
import static bookShelf.utils.book.GetAllBookMapping.getGetBookResponse;


@Service
public class BookServicesImpl implements BookServices {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public AddBookResponse addBook(AddBookRequest addBookRequest) {
        Book book = new Book();
        Book addedBook = addBookRequestMapping(book, addBookRequest);
        bookRepository.save(addedBook);
        AddBookResponse response = new AddBookResponse();
        response.setMessage("Book added successfully");
        return response;
    }

    @Override
    public GetAllBookResponse getBookByTitle(GetBookRequest getBookRequest) {
        List<Book> books = bookRepository.findByUserIdAndTitle(getBookRequest.getUserId(), getBookRequest.getTitle());
        bookIsNotFound(books);
        return getGetBookResponse(books);
    }

    @Override
    public GetAllBookResponse getBookByAuthor(GetBookRequest getBookRequest) {
        List<Book> books = bookRepository.findByUserIdAndAuthor(getBookRequest.getUserId(), getBookRequest.getAuthor());
        bookIsNotFound(books);
        return getGetBookResponse(books);
    }

    @Override
    public UpdateBookResponse updateBook(UpdateBookRequest updateBookRequest) {
        Book existingBook = bookRepository.findByUserIdAndTitleAndAuthor
                        (updateBookRequest.getUserId(),
                        updateBookRequest.getOriginalTitle(),
                        updateBookRequest.getOriginalAuthor())
                .orElseThrow(() -> new BookNotFound("Book not found"));
        Book updatedBook = updateBookRequestMapping(existingBook, updateBookRequest);

        bookRepository.save(updatedBook);
        return updateBookResponseMapping(existingBook);
    }


    @Override
    public DeleteBookResponse deleteBook(DeleteBookRequest deleteBookRequest) {
        Book book = bookRepository.findByUserIdAndTitleAndAuthor
                        (deleteBookRequest.getUserId(),
                        deleteBookRequest.getTitle(),
                        deleteBookRequest.getAuthor())
                .orElseThrow(() -> new BookNotFound("Book not found"));

        bookRepository.delete(book);

        DeleteBookResponse response = new DeleteBookResponse();
        response.setMessage("Book deleted successfully");
        return response;
    }


    @Override
    public GetAllBookResponse getAllBooks(GetBookRequest request) {
        List<Book> books = bookRepository.findAllByUserId(request.getUserId());
        return getGetBookResponse(books);
    }

    @Override
    public ReadBookResponse readBook(ReadBookRequest readBookRequest) {
        Book book = bookRepository.findByUserIdAndTitleAndAuthor(readBookRequest.getUserId(),
                        readBookRequest.getTitle(),
                        readBookRequest.getAuthor())
                .orElseThrow(() -> new BookNotFound("Book not found"));

        ReadBookResponse response = new ReadBookResponse();
        response.setDocument(book.getDocument());
        response.setTitle(book.getTitle());
        response.setAuthor(book.getAuthor());
        return response;
    }


    private static void bookIsNotFound (List<Book> books) {
        if(books.isEmpty()) throw new BookNotFound("Book not found");
    }


}
