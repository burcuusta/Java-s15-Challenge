package libraryModel.data;

import java.time.LocalDate;

public class LoanRecord {
    private Book book;
    private Reader reader;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public LoanRecord(Book book, Reader reader) {
        this.book = book;
        this.reader = reader;
        this.borrowDate = LocalDate.now();
        this.returnDate = null;
    }

    public Book getBook() {
        return book;
    }

    public Reader getReader() {
        return reader;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
