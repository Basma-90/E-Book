package src.interfaces;

public interface IBook {
  String getISBN();
  String getTitle();
  double getPrice();
  int getYear();
  boolean isOutdated(int currentYear, int maxAge);
}
