package libraryModel.data;

import java.time.LocalDate;

public class Bill {
    private int billId;
    private Reader reader;
    private Book book;
    private double amount;
    private LocalDate issueDate;

    public Bill(Reader reader, Book book, double amount) {
        this.billId = (int) (System.currentTimeMillis() % 100000);
        this.reader = reader;
        this.book = book;
        this.amount = amount;
        this.issueDate = LocalDate.now();
    }

    public int getBillId() {
        return billId;
    }

    public Reader getReader() {
        return reader;
    }

    public Book getBook() {
        return book;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public String generateBillText() {
        return "Fatura ID: " + billId + "\n" +
                "Tarih: " + issueDate + "\n" +
                "Okuyucu: " + reader.getName() + "\n" +
                "Kitap: " + book.getName() + "\n" +
                "Tutar: $" + amount;
    }
}
