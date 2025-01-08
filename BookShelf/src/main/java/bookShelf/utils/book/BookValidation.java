package bookShelf.utils.book;

import bookShelf.data.models.Book;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class BookValidation {

    public static void extractImageFromFirstPage(MultipartFile pdfFile, Book book) throws IOException {
        try(PDDocument document = PDDocument.load(pdfFile.getInputStream())) {
            PDFRenderer renderer = new PDFRenderer(document);
            if(document.getNumberOfPages() > 0) {
                BufferedImage bufferedImage = renderer.renderImageWithDPI(0, 300, ImageType.RGB);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", outputStream);
                book.setImage(outputStream.toByteArray());
            }
        }
    }
}
