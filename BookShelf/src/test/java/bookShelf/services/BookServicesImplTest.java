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
import java.io.InputStream;
import java.util.Optional;

import static bookShelf.utils.book.TestCase.createMockPdfFile;
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

    static InputStream pdfInputStream = BookServicesImplTest.class.getResourceAsStream("C:\\Users\\DELL  USER\\OneDrive\\Documents\\Assignment(Java).pdf");
    private static final MultipartFile pdf;

    static {
        try {
            pdf = new MockMultipartFile("document", "test.pdf", "application/pdf", pdfInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static AddBookRequest buildBookRequest(AddBookRequest bookRequest) throws IOException {
        bookRequest.setTitle("Jesus is lord");
        bookRequest.setAuthor("danjuma");
        bookRequest.setDescription("book description");
        bookRequest.setPdf(createMockPdfFile());
        bookRequest.setUserId("1");
        return bookRequest;
    }
    private static AddBookRequest buildBookRequest2(AddBookRequest bookRequest) throws IOException {
        bookRequest.setAuthor("chibuzo");
        bookRequest.setTitle("new title");
        bookRequest.setDescription("new description");
        bookRequest.setPdf(createMockPdfFile());
        bookRequest.setUserId("1");
        return bookRequest;
    }

    @Test void addBook() throws IOException {
        AddBookRequest bookRequest = new AddBookRequest();
        AddBookRequest addBook = buildBookRequest(bookRequest);
        AddBookResponse bookResponse = bookServices.addBook(addBook);

        assertNotNull(bookResponse);
        assertEquals("Book added successfully", bookResponse.getMessage());
        assertEquals(bookRepository.count(), 1);
    }

    @Test void getBookByTitle() throws IOException {
        AddBookRequest bookRequest = new AddBookRequest();
        AddBookRequest addBook = buildBookRequest(bookRequest);
        AddBookResponse addBookResponse = bookServices.addBook(addBook);

        assertNotNull(addBookResponse);
        assertEquals(addBookResponse.getMessage(), "Book added successfully");

        GetBookRequest getBookRequest = new GetBookRequest();
        getBookRequest.setUserId("1");
        getBookRequest.setTitle("Jesus is lord");
        GetAllBookResponse getBookResponse = bookServices.getBookByTitle(getBookRequest);

        assertNotNull(getBookResponse);
        assertEquals("1 Book(s) found", getBookResponse.getMessage());
        assertEquals(1, getBookResponse.getGetBookResponses().size());
    }

    @Test void updateBook() throws InterruptedException, IOException {
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
        assertEquals("Book Updated successfully", updateBookResponse.getMessage());
        assertEquals(bookRepository.count(), 1);
    }

    @Test void deleteBook() throws IOException {
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

    @Test void getAllBooks() throws IOException {
        AddBookRequest bookRequest1 = new AddBookRequest();
        AddBookRequest addBook1 = buildBookRequest(bookRequest1);
        bookServices.addBook(addBook1);

        AddBookRequest bookRequest2 = new AddBookRequest();
        AddBookRequest addBook2 = buildBookRequest2(bookRequest2);
        bookServices.addBook(addBook2);

        GetBookRequest request = new GetBookRequest();
        request.setUserId("1");
        GetAllBookResponse getAllBooksResponse = bookServices.getAllBooks(request);

        assertNotNull(getAllBooksResponse);
        assertEquals(2, getAllBooksResponse.getGetBookResponses().size());
    }


    @Test
    void getBookByAuthor() throws IOException {
        AddBookRequest bookRequest = new AddBookRequest();
        AddBookRequest addBook = buildBookRequest(bookRequest);
        AddBookResponse addBookResponse = bookServices.addBook(addBook);

        assertNotNull(addBookResponse);
        assertEquals(addBookResponse.getMessage(), "Book added successfully");

        GetBookRequest getBookRequest = new GetBookRequest();
        getBookRequest.setUserId("1");
        getBookRequest.setAuthor("danjuma");
        GetAllBookResponse getBookResponse = bookServices.getBookByAuthor(getBookRequest);

        assertNotNull(getBookResponse);
        assertEquals("1 Book(s) found", getBookResponse.getMessage());
        assertEquals(1,getBookResponse.getGetBookResponses().size());
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
        assertArrayEquals(readBookResponse.getDocument(), createMockPdfFile().getBytes());


    }
}
