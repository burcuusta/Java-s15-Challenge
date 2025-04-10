package libraryModel.services;

import libraryModel.data.Bill;
import libraryModel.data.Book;
import libraryModel.data.Reader;

public class BillingService {
    public Bill createBill(Reader reader, Book book) {
        double amount = 10.0;
        Bill bill = new Bill(reader, book, amount);
        reader.getBills().put(book, amount);
        System.out.println("✅ " + reader.getName() + " için " + book.getName() + " adlı kitap ödünç alındığında $" + amount + " tutarında fatura oluşturuldu.");
        return bill;
    }

    public double calculateFine(Book book) {

        return 0.0;
    }
}