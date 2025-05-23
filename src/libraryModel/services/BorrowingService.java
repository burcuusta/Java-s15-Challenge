package libraryModel.services;

import libraryModel.data.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BorrowingService {
    private List<LoanRecord> loanRecords;
    private Library library;
    private Map<Reader, MemberRecord> memberRecords;
    private BillingService billingService;

    public BorrowingService(Library library, Map<Reader, MemberRecord> memberRecords, BillingService billingService) {
        this.library = library;
        this.memberRecords = memberRecords;
        this.loanRecords = new ArrayList<>();
        this.billingService = billingService;
    }

    public boolean borrowBook(Reader reader, Book book) {
        MemberRecord memberRecord = memberRecords.get(reader);
        if (memberRecord != null && memberRecord.getNoBooksIssued() < memberRecord.getMaxBookLimit() && book.isAvailable()) {
            book.setAvailable(false);
            book.setOwner(reader);
            loanRecords.add(new LoanRecord(book, reader));
            memberRecord.incBookIssued();
            billingService.createBill(reader, book);
            return true;
        } else if (memberRecord == null) {
            System.out.println("❌ Okuyucu kaydı bulunamadı.");
        } else if (memberRecord.getNoBooksIssued() >= memberRecord.getMaxBookLimit()) {
            System.out.println("❌ " + reader.getName() + " adlı okuyucu maksimum kitap ödünç alma limitine ulaştı.");
        } else if (!book.isAvailable()) {
            System.out.println("❌ " + book.getName() + " adlı kitap şu anda ödünç alınmış durumda.");
        }
        return false;
    }

    public void returnBook(Reader reader, Book book) {
        LoanRecord loan = findLoanRecord(reader, book);
        if (loan != null && loan.getReturnDate() == null) {
            book.setAvailable(true);
            book.setOwner(null);
            loan.setReturnDate(LocalDate.now());
            MemberRecord memberRecord = memberRecords.get(reader);
            if (memberRecord != null) {
                memberRecord.decBookIssued();
            }
            if (reader.getBills().containsKey(book)) {
                double amount = reader.getBills().remove(book);
                System.out.println("✅ Kitap iade edildi. Ödenen ücret: $" + amount + " geri iade edildi.");
            } else {
                System.out.println("✅ Kitap iade edildi.");
            }
        } else {
            System.out.println("❌ Kitap iade edilemedi. Lütfen ödünç alma kaydını kontrol edin.");
        }
    }

    public List<Book> getBorrowedBooksForReader(Reader reader) {
        return loanRecords.stream()
                .filter(record -> record.getReader().equals(reader) && record.getReturnDate() == null)
                .map(LoanRecord::getBook)
                .collect(Collectors.toList());
    }

    private LoanRecord findLoanRecord(Reader reader, Book book) {
        for (LoanRecord record : loanRecords) {
            if (record.getBook().equals(book) && record.getReader().equals(reader) && record.getReturnDate() == null) {
                return record;
            }
        }
        return null;
    }
}
