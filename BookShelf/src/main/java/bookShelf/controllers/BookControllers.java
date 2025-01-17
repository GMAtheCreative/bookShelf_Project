package bookShelf.controllers;

import bookShelf.dtos.requests.book.*;
import bookShelf.dtos.responses.book.*;
import bookShelf.exception.bookException.BookNotFound;
import bookShelf.exception.bookException.MediaStorageException;
import bookShelf.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
class BookControllers {

    @Autowired
    private BookServices bookServices;

    // Add a new book
    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestBody AddBookRequest addBookRequest) {
        try {
            AddBookResponse response = bookServices.addBook(addBookRequest);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (MediaStorageException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while adding the book.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get a book by title
    @GetMapping("/title")
    public ResponseEntity<?> getBookByTitle(@RequestParam String userId, @RequestParam String title) {
        try {
            GetBookRequest request = new GetBookRequest();
            request.setUserId(userId);
            request.setTitle(title);
            GetAllBookResponse response = bookServices.getBookByTitle(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BookNotFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while fetching the book.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get a book by author
    @GetMapping("/author")
    public ResponseEntity<?> getBookByAuthor(@RequestParam String userId, @RequestParam String author) {
        try {
            GetBookRequest request = new GetBookRequest();
            request.setUserId(userId);
            request.setAuthor(author);
            GetAllBookResponse response = bookServices.getBookByAuthor(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BookNotFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while fetching the book.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update a book
    @PutMapping("/update")
    public ResponseEntity<?> updateBook(@RequestBody UpdateBookRequest updateBookRequest) {
        try {
            UpdateBookResponse response = bookServices.updateBook(updateBookRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BookNotFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while updating the book.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete a book
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteBook(@RequestBody DeleteBookRequest deleteBookRequest) {
        try {
            DeleteBookResponse response = bookServices.deleteBook(deleteBookRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BookNotFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while deleting the book.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all books for a user
    @GetMapping("/all")
    public ResponseEntity<?> getAllBooks(@RequestParam String userId) {
        try {
            GetBookRequest request = new GetBookRequest();
            request.setUserId(userId);
            GetAllBookResponse response = bookServices.getAllBooks(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while fetching all books.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Read a book (retrieve document)
    @GetMapping("/read")
    public ResponseEntity<?> readBook(@RequestParam String userId, @RequestParam String title, @RequestParam String author) {
        try {
            ReadBookRequest request = new ReadBookRequest();
            request.setUserId(userId);
            request.setTitle(title);
            request.setAuthor(author);
            ReadBookResponse response = bookServices.readBook(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BookNotFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while reading the book.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}