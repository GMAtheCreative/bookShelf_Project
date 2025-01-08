package bookShelf.services;

import bookShelf.data.models.Book;
import bookShelf.data.repositories.BookRepository;
import bookShelf.dtos.requests.book.*;
import bookShelf.dtos.responses.book.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class BookServicesImplTest {
    @Autowired
    private BookServices bookServices;
    @Autowired
    private BookRepository bookRepository;

    @BeforeEach public void setUp(){
        bookRepository.deleteAll();
    }
    private static final MultipartFile pdf = new MockMultipartFile("document", "test.pdf", "application/pdf", "test pdf content".getBytes());

    private static AddBookRequest buildBookRequest(AddBookRequest bookRequest) {
        bookRequest.setTitle("Jesus is lord");
        bookRequest.setAuthor("danjuma");
        bookRequest.setDescription("book description");
        bookRequest.setIsbn("1234566789");
        bookRequest.setPdf(pdf);
        bookRequest.setUserId("1");
        return bookRequest;
    }
    private static AddBookRequest buildBookRequest2(AddBookRequest bookRequest) {
        bookRequest.setIsbn("9876543210");
        bookRequest.setAuthor("chibuzo");
        bookRequest.setTitle("new title");
        bookRequest.setDescription("new description");
        bookRequest.setPdf(pdf);
        bookRequest.setUserId("1");
        return bookRequest;
    }

    @Test void addBook() {
        AddBookRequest bookRequest = new AddBookRequest();
        AddBookRequest addBook = buildBookRequest(bookRequest);
        AddBookResponse bookResponse = bookServices.addBook(addBook);

        assertNotNull(bookResponse);
        assertEquals("Book added successfully", bookResponse.getMessage());
        assertEquals(bookRepository.count(), 1);
    }

    @Test void getBookByTitle() {
        AddBookRequest bookRequest = new AddBookRequest();
        AddBookRequest addBook = buildBookRequest(bookRequest);
        AddBookResponse addBookResponse = bookServices.addBook(addBook);

        assertNotNull(addBookResponse);
        assertEquals(addBookResponse.getMessage(), "Book added successfully");

        GetBookRequest getBookRequest = new GetBookRequest();
        getBookRequest.setUserId("1");
        getBookRequest.setTitle("Jesus is lord");
        GetBookResponse getBookResponse = bookServices.getBookByTitle(getBookRequest);

        assertNotNull(getBookResponse);
        assertEquals(getBookResponse.getBooks().getFirst().getTitle(), addBook.getTitle());
        assertEquals("1 Book(s) found", getBookResponse.getMessage());
    }

    @Test void updateBook() throws InterruptedException {
        AddBookRequest bookRequest = new AddBookRequest();
        AddBookRequest addBook = buildBookRequest(bookRequest);
        AddBookResponse addBookResponse = bookServices.addBook(addBook);

        assertNotNull(addBookResponse);

        UpdateBookRequest updateBookRequest = new UpdateBookRequest();
        updateBookRequest.setOriginalTitle(bookRequest.getTitle());
        updateBookRequest.setOriginalAuthor(bookRequest.getAuthor());
        updateBookRequest.setTitle("Updated Title");
        updateBookRequest.setAuthor("Updated Author");
        updateBookRequest.setDescription("Updated Description");
        updateBookRequest.setUserId(addBook.getUserId());
        UpdateBookResponse updateBookResponse = bookServices.updateBook(updateBookRequest);

        assertNotNull(updateBookResponse);
        assertEquals("Update successful", updateBookResponse.getMessage());
        assertEquals(bookRepository.count(), 1);
    }

    @Test void deleteBook() {
        AddBookRequest bookRequest = new AddBookRequest();
        AddBookRequest addBook = buildBookRequest(bookRequest);
        AddBookResponse addBookResponse = bookServices.addBook(addBook);

        assertNotNull(addBookResponse);

        DeleteBookRequest deleteBookRequest = new DeleteBookRequest();
        deleteBookRequest.setTitle(addBook.getTitle());
        deleteBookRequest.setAuthor(addBook.getAuthor());
        deleteBookRequest.setUserId(addBook.getUserId());
        DeleteBookResponse deleteBookResponse = bookServices.deleteBook(deleteBookRequest);

        assertNotNull(deleteBookResponse);
        assertEquals("Book deleted successfully", deleteBookResponse.getMessage());
        Optional<Book> deletedBook = bookRepository.findByUserIdAndTitleAndAuthor(addBook.getUserId(), addBook.getTitle(), addBook.getAuthor());
        assertFalse(deletedBook.isPresent());
    }

    @Test void getAllBooks() {
        AddBookRequest bookRequest1 = new AddBookRequest();
        AddBookRequest addBook1 = buildBookRequest(bookRequest1);
        bookServices.addBook(addBook1);

        AddBookRequest bookRequest2 = new AddBookRequest();
        AddBookRequest addBook2 = buildBookRequest2(bookRequest2);
        bookServices.addBook(addBook2);

        GetBookRequest request = new GetBookRequest();
        request.setUserId("1");
        GetBookResponse getAllBooksResponse = bookServices.getAllBooks(request);

        assertNotNull(getAllBooksResponse);
        assertEquals(2, getAllBooksResponse.getBooks().size());
    }


    @Test
    void getBookByAuthor() {
        AddBookRequest bookRequest = new AddBookRequest();
        AddBookRequest addBook = buildBookRequest(bookRequest);
        AddBookResponse addBookResponse = bookServices.addBook(addBook);

        assertNotNull(addBookResponse);
        assertEquals(addBookResponse.getMessage(), "Book added successfully");

        GetBookRequest getBookRequest = new GetBookRequest();
        getBookRequest.setUserId("1");
        getBookRequest.setAuthor("danjuma");
        GetBookResponse getBookResponse = bookServices.getBookByAuthor(getBookRequest);

        assertNotNull(getBookResponse);
        assertEquals(getBookResponse.getBooks().getFirst().getTitle(), addBook.getTitle());
        assertEquals("1 Book(s) found", getBookResponse.getMessage());
    }

    @Test
    void readBook() throws IOException {
        AddBookRequest bookRequest = new AddBookRequest();
        AddBookRequest addBook = buildBookRequest(bookRequest);
        AddBookResponse addBookResponse = bookServices.addBook(addBook);

        assertNotNull(addBookResponse);
        assertEquals(addBookResponse.getMessage(), "Book added successfully");

        ReadBookRequest readBookRequest = new ReadBookRequest();
        readBookRequest.setUserId(addBook.getUserId());
        readBookRequest.setTitle(addBook.getTitle());
        readBookRequest.setAuthor(addBook.getAuthor());

        ReadBookResponse readBookResponse = bookServices.readBook(readBookRequest);
        assertNotNull(readBookResponse);
        assertEquals(addBook.getTitle(), readBookResponse.getTitle());
        assertEquals(addBook.getAuthor(), readBookResponse.getAuthor());
        assertArrayEquals(pdf.getBytes(), readBookResponse.getDocument());


    }
}
