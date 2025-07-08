package src.models;
import src.models.Book;
import src.services.MailService;
import src.interfaces.BooksForSale;
import src.interfaces.PaperBookSale;
import src.services.ShippingService;

public class PaperBook extends Book implements PaperBookSale, BooksForSale {
    private int stock;

    public PaperBook(String isbn, String title, double price, int year, int stock) {
        super(isbn, title, price, year);
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public String Buy(String ISBN, int quantity, String email, String address) {
        if (quantity <= 0) {
            return "Purchase failed. Quantity must be greater than zero.";
        }
        if (ISBN == null || ISBN.isEmpty()) {
            return "Purchase failed. ISBN cannot be null or empty.";
        }
        if (!ISBN.equals(getISBN())) {
            return "Purchase failed. ISBN does not match the book's ISBN.";
        }
        if (email == null || email.isEmpty()) {
            return "Purchase failed. Email cannot be null or empty.";
        }

        int newStock = reduceStock(ISBN, quantity);
        if (newStock <= 0) {
            return "Purchase failed. Not enough stock available.";
        }
    
        ShippingService shippingService = new ShippingService();
        shippingService.send(address);

        return "Purchase successful. A confirmation email has been sent to " + email + ".\n" +
               "Total price: " + (getPrice() * quantity) + "\n" +
               "Remaining stock: " + newStock;
    }
    
    @Override
    public int reduceStock(String ISBN, int quantity) {
        stock = getStock();
        if (stock >= quantity) {
            stock -= quantity;
            return stock;
        } else {
            return 0; 
        }
    }
}
