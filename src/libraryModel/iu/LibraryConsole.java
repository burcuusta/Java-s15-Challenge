package libraryModel.iu;


import libraryModel.data.*;
import libraryModel.services.BillingService;
import libraryModel.services.BookManagementService;
import libraryModel.services.BorrowingService;
import libraryModel.services.MembershipService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LibraryConsole {
    private Library library;
    private BookManagementService bookManagementService;
    private BorrowingService borrowingService;
    private MembershipService membershipService;
    private Librarian loggedInLibrarian;
    private Scanner scanner;

    public LibraryConsole() {
        this.library = new Library();
        Map<Reader, MemberRecord> memberRecords = new HashMap<>();
        this.membershipService = new MembershipService();
        this.bookManagementService = new BookManagementService(this.library);
        BillingService billingService = new BillingService();
        this.borrowingService = new BorrowingService(this.library, memberRecords, billingService);
        this.loggedInLibrarian = new Librarian("admin", "1234");
        this.loggedInLibrarian.setMembershipService(this.membershipService);
        this.scanner = new Scanner(System.in);
        addInitialData();
    }

    private void addInitialData() {
        Author tolkien = new Author("J.R.R. Tolkien");
        Author asimov = new Author("Isaac Asimov");
        Book hobbit = new Book(1, "The Hobbit", tolkien, "1st", "Fantasy");
        Book foundation = new Book(2, "Foundation", asimov, "1st", "Science Fiction");
        library.addBook(hobbit);
        library.addBook(foundation);
        library.addReader(new Student("Burcu"));
        library.addReader(new Faculty("faculty"));
        membershipService.registerMember(library.getReaders().get(0));
        membershipService.registerMember(library.getReaders().get(1));
    }

    public void run() {
        System.out.println("Kütüphane Otomasyon Sistemine Hoş Geldiniz!");
        while (true) {
            displayMenu();
            System.out.print("Lütfen bir işlem seçin: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addBook();
                    break;
                case "2":
                    findBook();
                    break;
                case "3":
                    updateBook();
                    break;
                case "4":
                    deleteBook();
                    break;
                case "5":
                    listBooksByCategory();
                    break;
                case "6":
                    listBooksByAuthor();
                    break;
                case "7":
                    borrowBook();
                    break;
                case "8":
                    returnBook();
                    break;
                case "9":
                    registerMember();
                    break;
                case "10":
                    listAllBooks();
                    break;
                case "11":
                    listAllReaders();
                    break;
                case "0":
                    System.out.println("Sistemden çıkılıyor...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Geçersiz seçim. Lütfen tekrar deneyin.");
            }
            System.out.println();
        }
    }

    private void displayMenu() {
        System.out.println("\n--- Menü ---");
        System.out.println("1. Yeni Kitap Ekle");
        System.out.println("2. Kitap Ara (ID, İsim)");
        System.out.println("3. Kitap Bilgilerini Güncelle");
        System.out.println("4. Kitap Sil");
        System.out.println("5. Kategoriye Göre Kitapları Listele");
        System.out.println("6. Yazara Göre Kitapları Listele");
        System.out.println("7. Kitap Ödünç Al");
        System.out.println("8. Kitap İade Et");
        System.out.println("9. Yeni Üye Kaydet");
        System.out.println("10. Tüm Kitapları Listele");
        System.out.println("11. Tüm Üyeleri Listele");
        System.out.println("0. Çıkış");
    }

    private void addBook() {
        System.out.print("Kitap Adı: ");
        String name = scanner.nextLine();
        System.out.print("Yazar Adı: ");
        String authorName = scanner.nextLine();
        Author author = new Author(authorName);
        System.out.print("Baskı: ");
        String edition = scanner.nextLine();
        System.out.print("Kategori: ");
        String category = scanner.nextLine();
        int id = (int) (System.currentTimeMillis() % 1000);
        Book newBook = new Book(id, name, author, edition, category);
        bookManagementService.addBook(newBook);
    }

    private void findBook() {
        System.out.println("Kitaba göre arama yapmak için 'isim' yazın, ID'ye göre aramak için 'id' yazın:");
        String searchType = scanner.nextLine().toLowerCase();
        if (searchType.equals("isim")) {
            System.out.print("Aranacak Kitap Adı: ");
            String name = scanner.nextLine();
            List<Book> books = bookManagementService.findBooksByName(name);
            if (books.isEmpty()) {
                System.out.println("Kitap bulunamadı.");
            } else {
                books.forEach(System.out::println);
            }
        } else if (searchType.equals("id")) {
            System.out.print("Aranacak Kitap ID: ");
            try {
                int id = Integer.parseInt(scanner.nextLine());
                Book book = bookManagementService.findBookById(id);
                if (book == null) {
                    System.out.println("Kitap bulunamadı.");
                } else {
                    System.out.println(book);
                }
            } catch (NumberFormatException e) {
                System.out.println("Geçersiz ID formatı.");
            }
        } else {
            System.out.println("Geçersiz arama türü.");
        }
    }

    private void updateBook() {
        System.out.print("Güncellenecek Kitap ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Book existingBook = bookManagementService.findBookById(id);
            if (existingBook == null) {
                System.out.println("Kitap bulunamadı.");
                return;
            }
            System.out.print("Yeni Kitap Adı (" + existingBook.getName() + "): ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                existingBook.setName(newName);
            }
            System.out.print("Yeni Yazar Adı (" + existingBook.getAuthor().getName() + "): ");
            String newAuthorName = scanner.nextLine();
            if (!newAuthorName.isEmpty()) {
                existingBook.setAuthor(new Author(newAuthorName));
            }
            System.out.print("Yeni Baskı (" + existingBook.getEdition() + "): ");
            String newEdition = scanner.nextLine();
            if (!newEdition.isEmpty()) {
                existingBook.setEdition(newEdition);
            }
            System.out.print("Yeni Kategori (" + existingBook.getCategoryName() + "): ");
            String newCategory = scanner.nextLine();
            if (!newCategory.isEmpty()) {
                existingBook.setCategoryName(newCategory);
            }
            bookManagementService.updateBook(existingBook);
        } catch (NumberFormatException e) {
            System.out.println("Geçersiz ID formatı.");
        }
    }

    private void deleteBook() {
        System.out.print("Silinecek Kitap ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            if (bookManagementService.deleteBook(id)) {
                System.out.println("Kitap silindi.");
            } else {
                System.out.println("Kitap silinemedi.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Geçersiz ID formatı.");
        }
    }

    private void listBooksByCategory() {
        System.out.print("Listelenecek Kategori Adı: ");
        String category = scanner.nextLine();
        List<Book> books = bookManagementService.listBooksByCategory(category);
        if (books.isEmpty()) {
            System.out.println("Bu kategoride kitap bulunamadı.");
        } else {
            System.out.println("--- " + category + " Kategorisindeki Kitaplar ---");
            books.forEach(System.out::println);
        }
    }

    private void listBooksByAuthor() {
        System.out.print("Listelenecek Yazar Adı: ");
        String authorName = scanner.nextLine();
        List<Book> books = bookManagementService.findBooksByAuthor(authorName);
        if (books.isEmpty()) {
            System.out.println("Bu yazara ait kitap bulunamadı.");
        } else {
            System.out.println("--- " + authorName + " Tarafından Yazılan Kitaplar ---");
            books.forEach(System.out::println);
        }
    }

    private void borrowBook() {
        System.out.print("Ödünç Alacak Üye Adı: ");
        String readerName = scanner.nextLine();
        Reader readerFromLibrary = library.getReaders().stream()
                .filter(r -> r.getName().equalsIgnoreCase(readerName))
                .findFirst().orElse(null);

        if (readerFromLibrary == null) {
            System.out.println("Üye bulunamadı.");
            return;
        }


        MemberRecord memberRecord = membershipService.getMemberRecord(readerFromLibrary);

        if (memberRecord == null) {
            System.out.println("❌ Okuyucu kaydı bulunamadı.");
            return;
        }

        Reader registeredReader = memberRecord.getMember();
        if (registeredReader == null) {
            System.out.println("❌ Kayıtlı üye nesnesi null.");
            return;
        }

        System.out.print("Ödünç Alınacak Kitap ID: ");
        try {
            int bookId = Integer.parseInt(scanner.nextLine());
            Book book = bookManagementService.findBookById(bookId);
            if (book == null) {
                System.out.println("Kitap bulunamadı.");
                return;
            }

            if (borrowingService.borrowBook(registeredReader, book)) {
                System.out.println("✅ " + book.getName() + " adlı kitap " + registeredReader.getName() + " tarafından ödünç alındı.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Geçersiz Kitap ID formatı.");
        }
    }

    private void returnBook() {
        System.out.print("Kitabı İade Eden Üye Adı: ");
        String readerName = scanner.nextLine();
        Reader reader = library.getReaders().stream()
                .filter(r -> r.getName().equalsIgnoreCase(readerName))
                .findFirst().orElse(null);
        if (reader == null) {
            System.out.println("Üye bulunamadı.");
            return;
        }
        System.out.print("İade Edilecek Kitap ID: ");
        try {
            int bookId = Integer.parseInt(scanner.nextLine());
            Book book = bookManagementService.findBookById(bookId);
            if (book == null) {
                System.out.println("Kitap bulunamadı.");
                return;
            }
            borrowingService.returnBook(reader, book);
        } catch (NumberFormatException e) {
            System.out.println("Geçersiz Kitap ID formatı.");
        }
    }

    private void registerMember() {
        System.out.print("Yeni Üye Adı: ");
        String readerName = scanner.nextLine();
        Reader newReader = new Student(readerName);
        library.addReader(newReader);
        membershipService.registerMember(newReader);
    }

    private void listAllBooks() {
        List<Book> books = bookManagementService.listAllBooks();
        if (books.isEmpty()) {
            System.out.println("Kütüphanede hiç kitap bulunmamaktadır.");
        } else {
            System.out.println("--- Tüm Kitaplar ---");
            books.forEach(System.out::println);
        }
    }

    private void listAllReaders() {
        List<Reader> readers = library.getReaders();
        if (readers.isEmpty()) {
            System.out.println("Sistemde hiç üye bulunmamaktadır.");
        } else {
            System.out.println("--- Tüm Üyeler ---");
            readers.forEach(r -> System.out.println(r.whoYouAre() + ": " + r.getName()));
        }
    }

    public static void main(String[] args) {
        LibraryConsole console = new LibraryConsole();
        console.run();
    }
}