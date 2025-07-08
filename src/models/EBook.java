package src.models;
import src.services.MailService;
import src.interfaces.BooksForSale;
import src.interfaces.EBookSale;

public class EBook extends Book implements EBookSale{
    private String fileType;
    private MailService mailService;
    private static final int MAX_QUANTITY = 1; 

    public EBook(String isbn, String title, double price, int year, String fileType, MailService mailService) {
        super(isbn, title, price, year);
        this.fileType = fileType;
        this.mailService = mailService;
    }

    @Override
    public String Buy(String ISBN, int quantity, String email) {
        if (quantity > MAX_QUANTITY) {
            return "You can only buy one copy of an eBook at a time.";
        }
        String message = "Thank you for purchasing the eBook: " + getTitle() + ".\n" +
                         "You can download it from the following link: [Download Link]";
        mailService.sendEmail(email, fileType);
        double price = getPrice();
        return "Purchase successful. A confirmation email has been sent to " + email + ".\n" +
               "Total price: " + price;
    }

    public String getFileType() {
        return fileType;
    }
}
