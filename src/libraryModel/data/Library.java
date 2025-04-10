package libraryModel.data;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<Reader> readers;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Reader> getReaders() {
        return readers;
    }

    public void setReaders(List<Reader> readers) {
        this.readers = readers;
    }

    public Library() {
        books = new ArrayList<>();
        readers = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public Book findBookById(int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }

    public List<Book> findBooksByAuthor(String authorName) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().getName().equalsIgnoreCase(authorName)) {
                result.add(book);
            }
        }
        return result;
    }

    public void addReader(Reader reader) {
        readers.add(reader);
    }

    public List<Book> listBooks() {
        return books;
    }

    public List<Reader> listReaders() {
        return readers;
    }

    public boolean removeBookById(int id) {
        return books.removeIf(book -> book.getId() == id);
    }
}
