package bookShelf.utils.book;

import bookShelf.data.models.Book;
import bookShelf.dtos.requests.book.AddBookRequest;
import bookShelf.dtos.requests.book.UpdateBookRequest;
import bookShelf.dtos.responses.book.UpdateBookResponse;
import bookShelf.exception.bookException.MediaStorageException;

import java.io.IOException;
import java.sql.SQLOutput;

import static bookShelf.utils.book.BookValidation.extractImageFromFirstPage;

public class BookMapping {

    public static Book addBookRequestMapping (Book book, AddBookRequest addBookRequest) {

        book.setAuthor(addBookRequest.getAuthor());
        book.setIsbn(addBookRequest.getIsbn());
        book.setDescription(addBookRequest.getDescription());
        book.setTitle(addBookRequest.getTitle());
        book.setUserId(addBookRequest.getUserId());

        try {
            if (addBookRequest.getPdf() != null) {
                book.setDocument(addBookRequest.getPdf().getBytes());
//                extractImageFromFirstPage(addBookRequest, book);
                System.out.println("bok enter if");
            }
            else{
                throw new MediaStorageException("media is empty");
            }
        }
        catch (IOException e) {
            System.out.println("it never entered the extract method");
            throw new MediaStorageException("Could not store media files. Please try again!", e);
        }
        return book;
    }


    public static Book updateBookRequestMapping (Book existingBook, UpdateBookRequest updateBookRequest) {
        existingBook.setTitle(updateBookRequest.getTitle());
        existingBook.setAuthor(updateBookRequest.getAuthor());
        existingBook.setDescription(updateBookRequest.getDescription());
        existingBook.setUserId(updateBookRequest.getUserId());
        existingBook.setId(existingBook.getId());
        existingBook.setImage(existingBook.getImage());
        return existingBook;
    }

    public static UpdateBookResponse updateBookResponseMapping (Book existingBook) {
        UpdateBookResponse response = new UpdateBookResponse();
        response.setMessage("Book Updated successfully");
        response.setImage(existingBook.getImage());
        response.setTitle(existingBook.getTitle());
        response.setAuthor(existingBook.getAuthor());
        response.setDescription(existingBook.getDescription());
        return response;
    }
}
