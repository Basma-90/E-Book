package src.models;
import java.util.*;


public class Inventory {
    private Map<String, Book> books = new HashMap<>();

    public void addBook(Book book) {
        books.put(book.getISBN(), book);
    }

    public void removeOutdatedBooks(int currentYear, int maxAge) {
        books.values().removeIf(book -> book.isOutdated(currentYear, maxAge));
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books.values());
    }

    public String buyBookByISBN(String isbn, int quantity, String email, String address) {
        Book book = books.get(isbn);
        if(book instanceof ShowcaseBook) {
            throw new UnsupportedOperationException("Showcase books are not for sale");
        }
        if(book instanceof EBook) {
            return ((EBook) book).Buy(isbn, quantity, email);
        } else if(book instanceof PaperBook) {
            return ((PaperBook) book).Buy(isbn, quantity, email, address);
        } else {
            return "Book not found or not available for sale.";
        }
    }
}
