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

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanRecord that = (LoanRecord) o;
        return book.equals(that.book) && reader.equals(that.reader) && borrowDate.equals(that.borrowDate);
    }

    @Override
    public int hashCode() {
        int result = book.hashCode();
        result = 31 * result + reader.hashCode();
        result = 31 * result + borrowDate.hashCode();
        return result;
    }

}
