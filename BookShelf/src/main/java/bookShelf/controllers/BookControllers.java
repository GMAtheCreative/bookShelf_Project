package bookShelf.controllers;

import bookShelf.dtos.requests.book.AddBookRequest;
import bookShelf.dtos.responses.book.AddBookResponse;
import bookShelf.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/BookShelf")
public class BookControllers {
        @Autowired
        private  BookServices bookServices;

        @PostMapping("/addBook")
        public AddBookResponse addBook(@RequestBody AddBookRequest addBookRequest) {
//            AddBookResponse addBookResponse =
            return bookServices.addBook(addBookRequest);
//            return new ResponseEntity<>(addBookResponse, HttpStatus.CREATED);
        }

}
