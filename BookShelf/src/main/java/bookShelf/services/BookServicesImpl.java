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
import java.util.List;

import static bookShelf.utils.book.BookValidation.extractImageFromFirstPage;


@Service
public class BookServicesImpl implements BookServices {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public AddBookResponse addBook(AddBookRequest addBookRequest) {
        Book book = new Book();
        book.setAuthor(addBookRequest.getAuthor());
        book.setIsbn(addBookRequest.getIsbn());
        book.setDescription(addBookRequest.getDescription());
        book.setTitle(addBookRequest.getTitle());
        book.setUserId(addBookRequest.getUserId());

        try {
            if (addBookRequest.getPdf() != null) {
                book.setDocument(addBookRequest.getPdf().getBytes());
                extractImageFromFirstPage(addBookRequest.getPdf(), book);
            }
        }
        catch (IOException e) {
            throw new MediaStorageException("Could not store media files. Please try again!", e);
        }
        bookRepository.save(book);
        AddBookResponse response = new AddBookResponse();
        response.setMessage("Book added successfully");
        return response;
    }



    @Override
    public GetBookResponse getBookByTitle(GetBookRequest getBookRequest) {
        List<Book> books = bookRepository.findByUserIdAndTitle(getBookRequest.getUserId(), getBookRequest.getTitle());
        if (books.isEmpty()) {
            throw new BookNotFound("Book not found");
        }
        GetBookResponse response = new GetBookResponse();
        response.setBooks(books);
        int numberOfBooks = response.getBooks().size();
        response.setMessage(numberOfBooks + " Book(s) found");
        return response;
    }

    @Override
    public GetBookResponse getBookByAuthor(GetBookRequest getBookRequest) {
//        GetBookRequest request = new GetBookRequest();
        List<Book> books = bookRepository.findByUserIdAndAuthor(getBookRequest.getUserId(), getBookRequest.getAuthor());
        if (books.isEmpty()) {
            throw new BookNotFound("Book not found");
        }
        GetBookResponse response = new GetBookResponse();
        response.setBooks(books);
        int numberOfBooks = response.getBooks().size();
        response.setMessage(numberOfBooks + " Book(s) found");
        return response;
    }


    @Override
    public UpdateBookResponse updateBook(UpdateBookRequest updateBookRequest) {
        Book existingBook = bookRepository.findByUserIdAndTitleAndAuthor
                        (updateBookRequest.getUserId(),
                        updateBookRequest.getOriginalTitle(),
                        updateBookRequest.getOriginalAuthor())
                .orElseThrow(() -> new BookNotFound("Book not found"));

        existingBook.setTitle(updateBookRequest.getTitle());
        existingBook.setAuthor(updateBookRequest.getAuthor());
        existingBook.setDescription(updateBookRequest.getDescription());
        existingBook.setUserId(updateBookRequest.getUserId());

        try {
            if (updateBookRequest.getImage() != null) {
                existingBook.setImage(updateBookRequest.getImage().getBytes());
            }
            if (updateBookRequest.getPdf() != null) {
                existingBook.setDocument(updateBookRequest.getPdf().getBytes());
            }
        } catch (IOException e) {
            throw new MediaStorageException("Could not store media files. Please try again!", e);
        }

        Book updatedBook = bookRepository.save(existingBook);

        UpdateBookResponse response = new UpdateBookResponse();
        response.setMessage("Update successful");

        return response;
    }


    @Override
    public DeleteBookResponse deleteBook(DeleteBookRequest deleteBookRequest) {
        Book book = bookRepository.findByUserIdAndTitleAndAuthor(deleteBookRequest.getUserId(),
                        deleteBookRequest.getTitle(),
                        deleteBookRequest.getAuthor())
                .orElseThrow(() -> new BookNotFound("Book not found"));

        bookRepository.delete(book);

        DeleteBookResponse response = new DeleteBookResponse();
        response.setMessage("Book deleted successfully");
        return response;
    }


    @Override
    public GetBookResponse getAllBooks(GetBookRequest request) {
        List<Book> books = bookRepository.findAllByUserId(request.getUserId());
        GetBookResponse response = new GetBookResponse();
        response.setBooks(books);
        response.setMessage(books.size() + " Book(s) found");
        return response;
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



}
