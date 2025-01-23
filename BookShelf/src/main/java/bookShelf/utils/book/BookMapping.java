package bookShelf.utils.book;

import bookShelf.data.models.Book;
import bookShelf.dtos.requests.book.AddBookRequest;
import bookShelf.dtos.requests.book.UpdateBookRequest;
import bookShelf.dtos.responses.book.UpdateBookResponse;
import bookShelf.exception.bookException.MediaStorageException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

import static bookShelf.utils.book.BookValidation.extractImageFromFirstPage;
import static sun.font.CreatedFontTracker.MAX_FILE_SIZE;

@Slf4j
public class BookMapping {

    public static Book addBookRequestMapping(Book book, AddBookRequest addBookRequest) {
        book.setAuthor(addBookRequest.getAuthor());
        book.setDescription(addBookRequest.getDescription());
        book.setTitle(addBookRequest.getTitle());
        book.setUserId(addBookRequest.getUserId());

        log.info("book:: {}", addBookRequest);
        if (addBookRequest.getPdf() == null || addBookRequest.getPdf().isEmpty()) {
            throw new MediaStorageException("PDF file is required");
        }

        if (addBookRequest.getPdf().getSize() > MAX_FILE_SIZE) {
            throw new MediaStorageException("File size exceeds the maximum allowed limit of 100MB");
        }

        try (InputStream inputStream = addBookRequest.getPdf().getInputStream()) {
            byte[] fileBytes = inputStream.readAllBytes();
            book.setDocument(fileBytes);
            extractImageFromFirstPage(addBookRequest, book);
        } catch (IOException e) {
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
