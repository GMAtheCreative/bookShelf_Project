package bookShelf.utils.book;

import bookShelf.data.models.Book;
import bookShelf.dtos.requests.book.AddBookRequest;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class BookValidation {

    public static void extractImageFromFirstPage(AddBookRequest addBookRequest, Book book){
        try(PDDocument document = PDDocument.load(addBookRequest.getPdf().getInputStream())) {
            System.out.println("pdf loaded succesfully");
            PDFRenderer renderer = new PDFRenderer(document);
            if(document.getNumberOfPages() > 0) {
                BufferedImage bufferedImage = renderer.renderImageWithDPI(0, 300, ImageType.RGB);
                if(bufferedImage != null) {
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    ImageIO.write(bufferedImage, "png", outputStream);
                    book.setImage(outputStream.toByteArray());
                    System.out.println("image was extracted");
                }
                else{
                    BufferedImage defaultImage = generateDefaultImage(addBookRequest.getTitle());
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    ImageIO.write(defaultImage, "png", outputStream);
                    book.setImage(outputStream.toByteArray());
                    System.out.println("default image was created");
                }
            }
            else{
                System.out.println("pdf has no page");
            }
        }
        catch (IOException e) {
            System.out.println("error extracting image frm page.." + e.getMessage());
            throw new RuntimeException("image never extracted ",e);
        }
    }




    private static BufferedImage generateDefaultImage(String title) {
        int width = 400;
        int height = 200;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(Color.lightGray);
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(Color.black);
        graphics.setFont(new Font("Times New Roman", Font.BOLD, 20));
        FontMetrics metrics = graphics.getFontMetrics();
        int x = (width - metrics.stringWidth(title)) / 2;
        int y = (height - metrics.getHeight()) / 2;
        graphics.drawString(title, x, y);
        graphics.dispose();
        return image;
    }
}




