package libraryModel.data;

public class Bill {
    private Reader reader;
    private Book book;
    private double amount;

    public Bill(Reader reader, Book book, double amount) {
        this.reader = reader;
        this.book = book;
        this.amount = amount;
    }

    public String generateBillText() {
        return "Bill for " + reader.getName() + " | Book: " + book.getName() + " | Amount: $" + amount;
    }
}
