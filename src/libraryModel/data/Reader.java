package libraryModel.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Reader extends Person {
    private static final int MAX_BOOK_LIMIT = 5;
    private Set<Book> borrowedBooks;
    private Map<Book, Double> bills;

    public Reader(String name) {
        super(name);
        this.borrowedBooks = new HashSet<>();
        this.bills = new HashMap<>();
    }

    public boolean borrowBook(Book book) {
        if (borrowedBooks.size() < MAX_BOOK_LIMIT && book.isAvailable()) {
            borrowedBooks.add(book);
            book.setOwner(this);
            book.setAvailable(false);
            return true;
        }
        return false;
    }

    public boolean returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.setAvailable(true);
            book.setOwner(null);
            bills.remove(book);
            return true;
        }
        return false;
    }

    public Map<Book, Double> getBills() {
        return bills;
    }

    public Set<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    @Override
    public String whoYouAre() {
        return "Reader: " + name;
    }
}
