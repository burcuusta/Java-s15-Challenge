package libraryModel.services;

import libraryModel.data.Bill;
import libraryModel.data.Book;
import libraryModel.data.Reader;

public class BillingService {
    public double calculateFine(Book book) {
        return 5.0;
    }

    public Bill createBill(Reader reader, Book book) {
        double fineAmount = calculateFine(book);
        Bill bill = new Bill(reader, book, fineAmount);
        reader.getBills().put(book, fineAmount);
        return bill;
    }

    private int generateBillId() {
        return (int) (System.currentTimeMillis() % 100000);
    }

}
