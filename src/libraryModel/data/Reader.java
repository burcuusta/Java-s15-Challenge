package libraryModel.data;

import java.util.*;

public abstract class Reader extends Person {
    protected static final int MAX_BOOK_LIMIT = 5;
    private Set<Book> borrowedBooks;
    private Map<Book, Double> bills;

    public Reader(String name) {
        super(name);
        this.borrowedBooks = new HashSet<>();
        this.bills = new HashMap<>();
    }

    public Set<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public Map<Book, Double> getBills() {
        return bills;
    }


    @Override
    public abstract String whoYouAre();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return Objects.equals(name, reader.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}