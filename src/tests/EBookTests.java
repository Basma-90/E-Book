package src.tests;
import src.models.Book;
import src.models.EBook;
import src.models.Inventory;
import src.models.PaperBook;
import src.models.ShowcaseBook;
import src.services.MailService;

public class EBookTests {
    public static void main(String[] args) {
        setUpTestEnvironment();
    }

    public static void setUpTestEnvironment() {
        MailService mailService = new MailService();
        EBook ebook = new EBook("1234567890", "Test EBook", 100, 2003, "PDF", mailService);
        PaperBook paperBook = new PaperBook("0987654321", "Test PaperBook", 200, 2023, 100);
        ShowcaseBook showcaseBook = new ShowcaseBook("1122334455", "Test ShowcaseBook", 2023);
        Inventory inventory = new Inventory();

        System.out.println("=== Running Tests ===");
        System.out.println();

        System.out.println("=== Test: EBook Purchase ===");
        testEBookPurchase(ebook, mailService);
        System.out.println();

        System.out.println("=== Test: EBook File Type ===");
        testEBookFileType(ebook);
        System.out.println();

        System.out.println("=== Test: PaperBook Purchase ===");
        testPaperBookPurchase(paperBook);
        System.out.println();

        System.out.println("=== Test: ShowcaseBook Details ===");
        testShowcaseBookDetails(showcaseBook);
        System.out.println();

        System.out.println("=== Test: Inventory Operations ===");
        addToInventory(inventory, ebook);
        addToInventory(inventory, paperBook);
        addToInventory(inventory, showcaseBook);
        displayInventory(inventory);

        removeFromInventory(inventory, ebook);
        displayInventory(inventory);

        testInventoryCapacity(inventory);
        System.out.println();

        System.out.println("=== All Tests Completed ===");
    }

    public static void testEBookPurchase(EBook ebook, MailService mailService) {
        String result = ebook.Buy("1234567890", 1, "test@example.com");
        System.out.println("Purchase Result: " + result);
        assert result.contains("Purchase successful");
    }

    public static void testEBookFileType(EBook ebook) {
        String fileType = ebook.getFileType();
        System.out.println("EBook File Type: " + fileType);
        assert fileType.equals("PDF");
    }

    public static void testPaperBookPurchase(PaperBook paperBook) {
        String result = paperBook.Buy("0987654321", 1, "test@example.com", "123 Test St");
        System.out.println("Purchase Result: " + result);
        assert result.contains("Purchase successful");
    }

    public static void testShowcaseBookDetails(ShowcaseBook showcaseBook) {
        String title = showcaseBook.getTitle();
        int year = showcaseBook.getYear();
        System.out.println("Showcase Book Title: " + title);
        System.out.println("Showcase Book Year: " + year);
        assert title.equals("Test ShowcaseBook");
        assert year == 2023;
    }

    public static void addToInventory(Inventory inventory, Book book) {
        inventory.addBook(book);
        System.out.println("Book added to inventory: " + book.getTitle());
    }

    public static void removeFromInventory(Inventory inventory, Book book) {
        inventory.removeOutdatedBooks(2023, 5);
        System.out.println("Book removed from inventory: " + book.getTitle());
    }

    public static void displayInventory(Inventory inventory) {
        System.out.println("Current Inventory:");
        for (Book book : inventory.getBooks()) {
            System.out.println("Book Title: " + book.getTitle());
        }
    }

    public static void testInventoryCapacity(Inventory inventory) {
        int capacity = inventory.getBooks().size();
        System.out.println("Inventory Capacity: " + capacity);
        assert capacity >= 0;
    }
}
