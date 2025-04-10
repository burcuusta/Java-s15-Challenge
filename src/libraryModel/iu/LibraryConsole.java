package libraryModel.iu;

import libraryModel.*;
import libraryModel.data.*;
import libraryModel.services.BillingService;
import libraryModel.services.BorrowingService;
import libraryModel.services.MembershipService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LibraryConsole {
    private static Scanner scanner = new Scanner(System.in);
    private static Library library = new Library();
    private static Map<Reader, MemberRecord> memberRecords = new HashMap<>();
    private static BillingService billingService = new BillingService();
    private static BorrowingService borrowingService = new BorrowingService(library, memberRecords, billingService);
    private static MembershipService membershipService = new MembershipService(memberRecords);
    private static Librarian librarian = new Librarian("Admin", "admin123", borrowingService, membershipService);


    public static void main(String[] args) {
        System.out.println("📚 Kütüphane Otomasyon Sistemine Hoşgeldiniz!");
        seedData();

        boolean running = true;
        while (running) {
            showMenu();
            int choice = getInput("Seçiminiz: ");

            switch (choice) {
                case 1 -> addNewBook();
                case 2 -> searchBook();
                case 3 -> updateBook();
                case 4 -> deleteBook();
                case 5 -> listBooksByCategory();
                case 6 -> listBooksByAuthor();
                case 7 -> borrowBook();
                case 8 -> returnBook();
                case 9 -> running = false;
                default -> System.out.println("Geçersiz seçim, tekrar deneyin.");
            }
        }
        System.out.println("👋 Sistemden çıkılıyor...");
    }

    private static void showMenu() {
        System.out.println("\n=== Ana Menü ===");
        System.out.println("1. Yeni Kitap Ekle");
        System.out.println("2. Kitap Ara (ID/İsim/Yazar)");
        System.out.println("3. Kitap Bilgilerini Güncelle");
        System.out.println("4. Kitap Sil");
        System.out.println("5. Kategoriye Göre Kitapları Listele");
        System.out.println("6. Yazara Göre Kitapları Listele");
        System.out.println("7. Kitap Ödünç Al");
        System.out.println("8. Kitap Geri İade Et");
        System.out.println("9. Çıkış");
    }

    private static int getInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) scanner.next();
        return scanner.nextInt();
    }

    private static String getLine(String prompt) {
        System.out.print(prompt);
        scanner.nextLine();
        return scanner.nextLine();
    }

    // Menü İşlemleri:

    private static void addNewBook() {
        String name = getLine("Kitap Adı: ");
        String authorName = getLine("Yazar Adı: ");
        String edition = getLine("Baskı: ");

        Author author = new Author(authorName);
        Book book = new Book(name, author, edition);
        library.addBook(book);

        System.out.println("📘 Kitap başarıyla eklendi: " + book.getName());
    }

    private static void searchBook() {
        String keyword = getLine("ID, İsim veya Yazar adı giriniz: ");
        List<Book> found = library.getBooks().stream()
                .filter(b -> String.valueOf(b.getId()).equals(keyword)
                        || b.getName().equalsIgnoreCase(keyword)
                        || b.getAuthor().getName().equalsIgnoreCase(keyword))
                .toList();

        if (found.isEmpty()) {
            System.out.println("❌ Kitap bulunamadı.");
        } else {
            found.forEach(b -> System.out.println("✅ " + b));
        }
    }

    private static void updateBook() {
        int id = getInput("Güncellenecek kitabın ID'si: ");
        Book book = library.findBookById(id);

        if (book != null) {
            String newName = getLine("Yeni Adı (" + book.getName() + "): ");
            String newEdition = getLine("Yeni Baskı (" + book.getEdition() + "): ");
            book.setName(newName);
            book.setEdition(newEdition);
            System.out.println("✅ Kitap bilgileri güncellendi.");
        } else {
            System.out.println("❌ Kitap bulunamadı.");
        }
    }

    private static void deleteBook() {
        int id = getInput("Silinecek kitabın ID'si: ");
        if (library.removeBookById(id)) {
            System.out.println("🗑️ Kitap silindi.");
        } else {
            System.out.println("❌ Kitap bulunamadı.");
        }
    }

    private static void listBooksByCategory() {
        String category = getLine("Kategori adı giriniz: ");
        List<Book> list = library.getBooks().stream()
                .filter(b -> b instanceof BookCategory
                        && ((BookCategory) b).getCategoryName().equalsIgnoreCase(category))
                .toList();

        list.forEach(b -> System.out.println("📚 " + b));
        if (list.isEmpty()) System.out.println("❌ Bu kategoride kitap bulunamadı.");
    }

    private static void listBooksByAuthor() {
        String author = getLine("Yazar adı giriniz: ");
        List<Book> list = library.getBooks().stream()
                .filter(b -> b.getAuthor().getName().equalsIgnoreCase(author))
                .toList();

        list.forEach(b -> System.out.println("📚 " + b));
        if (list.isEmpty()) System.out.println("❌ Bu yazara ait kitap bulunamadı.");
    }

    private static void registerNewMember() {
        String name = getLine("Yeni üyenin adı: ");
        Reader newReader = new Reader(name);
        MemberRecord record = membershipService.registerMember(newReader);
        System.out.println("✅ " + newReader.getName() + " başarıyla üye olarak kaydedildi. Üye ID: " + record.getMemberId());
    }

    private static void borrowBook() {
        String userName = getLine("Adınız: ");
        Reader reader = findOrCreateReader(userName);
        if (!membershipService.verifyMember(reader)) {
            System.out.println("❌ Bu kullanıcı üye değil. Lütfen önce üye olun.");
            return;
        }
        int id = getInput("Ödünç almak istediğiniz kitap ID'si: ");
        Book book = library.findBookById(id);

        if (librarian.issueBook(reader, book)) {
            System.out.println("✅ Kitap ödünç alındı.");
        } else {
            System.out.println("❌ Kitap ödünç alınamadı.");
        }
    }


    private static void returnBook() {
        String userName = getLine("Adınız: ");
        Reader reader = new Reader(userName);
        int id = getInput("İade edilecek kitap ID'si: ");
        Book book = library.findBookById(id);

        if (book != null) {
            librarian.returnBook(reader, book);
        } else {
            System.out.println("❌ Kitap bulunamadı.");
        }
    }
    private static Reader findOrCreateReader(String userName) {
        return library.getReaders().stream()
                .filter(r -> r.getName().equalsIgnoreCase(userName))
                .findFirst()
                .orElseGet(() -> {
                    Reader newReader = new Reader(userName);
                    library.addReader(newReader);
                    return newReader;
                });
    }


    private static void seedData() {
        Author orwell = new Author("George Orwell");
        Author rowling = new Author("J.K. Rowling");

        library.addBook(new Book("1984", orwell, "1. Baskı"));
        library.addBook(new Book("Hayvan Çiftliği", orwell, "2. Baskı"));
        library.addBook(new Book("Harry Potter", rowling, "1. Baskı"));
    }
}