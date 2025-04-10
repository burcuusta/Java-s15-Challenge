package libraryModel.services;



import libraryModel.data.Book;
import libraryModel.data.Library;

import java.util.List;
import java.util.stream.Collectors;

public class BookManagementService {
    private Library library;

    public BookManagementService(Library library) {
        this.library = library;
    }

    public void addBook(Book book) {
        if (book != null) {
            library.addBook(book);
            System.out.println("✅ Kitap başarıyla eklendi: " + book.getName());
        } else {
            System.out.println("❌ Geçersiz kitap nesnesi.");
        }
    }

    public Book findBookById(int id) {
        return library.findBookById(id);
    }

    public List<Book> findBooksByName(String name) {
        return library.getBooks().stream()
                .filter(book -> book.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public List<Book> findBooksByAuthor(String authorName) {
        return library.findBooksByAuthor(authorName);
    }

    public void updateBook(Book updatedBook) {
        Book existingBook = findBookById(updatedBook.getId());
        if (existingBook != null) {
            existingBook.setName(updatedBook.getName());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setEdition(updatedBook.getEdition());
            existingBook.setCategoryName(updatedBook.getCategoryName());
            System.out.println("✅ Kitap bilgileri güncellendi: " + updatedBook.getName());
        } else {
            System.out.println("❌ Belirtilen ID'ye sahip kitap bulunamadı.");
        }
    }

    public boolean deleteBook(int id) {
        if (library.removeBookById(id)) {
            System.out.println("✅ Kitap başarıyla silindi (ID: " + id + ")");
            return true;
        } else {
            System.out.println("❌ Belirtilen ID'ye sahip kitap bulunamadı.");
            return false;
        }
    }

    public List<Book> listBooksByCategory(String category) {
        return library.getBooks().stream()
                .filter(book -> book.getCategoryName().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public List<Book> listAllBooks() {
        return library.getBooks();
    }
}