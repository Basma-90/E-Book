package src.models;
import src.models.Book;

public class ShowcaseBook extends Book {
    public ShowcaseBook(String isbn, String title, int year) {
        super(isbn, title, 0.0, year);
    }

    public double buy(int quantity, String email, String address) {
        throw new UnsupportedOperationException("Showcase books are not for sale");
    }
    
}
