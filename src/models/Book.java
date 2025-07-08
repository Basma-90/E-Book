package src.models;
import src.interfaces.IBook;
public class Book implements IBook {
    private String ISBN;
    private String title;
    private int year;
    private double price;

    public Book(String ISBN, String title, double price, int year ){
        this.ISBN=ISBN;
        this.title=title;
        this.price=price;
        this.year=year;
    }

    public double getPrice(){
          return this.price;
    }

    public int getYear(){
        return this.year;
    }

    public String getTitle(){
        return this.title;
    }

    public String getISBN(){
        return this.ISBN;
    }

    public boolean isOutdated(int currentYear ,  int maxAge){
        int publishYear=this.year;
        if(currentYear-publishYear >=maxAge){
            return true;
        }
        return false;
    }

    public String Buy(String ISBN, int quantity, String email, String address) {
        return "This method should be overridden in subclasses.";
    }
}
