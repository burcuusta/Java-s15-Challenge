package libraryModel.data;

import java.util.ArrayList;
import java.util.List;

public class Author extends Person {
    private List<Book> books;

    public Author(String name) {
        super(name);
        this.books = new ArrayList<>();
    }

    public void newBook(Book book) {
        books.add(book);
    }

    public void showBooks() {
        System.out.println("Books written by " + name + ":");
        if (books.isEmpty()) {
            System.out.println("This author has no books yet.");
        } else {
            for (Book book : books) {
                System.out.println("- " + book.getName());
            }
        }
    }

    public List<Book> getBooks() {
        return List.copyOf(books);
    }

    @Override
    public String whoYouAre() {
        return "Author: " + name;
    }
}