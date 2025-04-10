package libraryModel.data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books;
    private List<Reader> readers;

    public Library() {
        this.books = new ArrayList<>();
        this.readers = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Reader> getReaders() {
        return readers;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public Book findBookById(int id) {
        return this.books.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }

    public List<Book> findBooksByName(String name) {
        return this.books.stream().filter(b -> b.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
    }

    public List<Book> findBooksByAuthor(String authorName) {
        return this.books.stream().filter(b -> b.getAuthor().getName().equalsIgnoreCase(authorName)).collect(Collectors.toList());
    }

    public List<Book> findBooksByCategory(String category) {
        return this.books.stream().filter(b -> b.getCategoryName().equalsIgnoreCase(category)).collect(Collectors.toList());
    }

    public void addReader(Reader reader) {
        this.readers.add(reader);
    }

    public boolean removeBookById(int id) {
        return this.books.removeIf(book -> book.getId() == id);
    }
}