package libraryModel.data;

import libraryModel.services.BorrowingService;
import libraryModel.services.MembershipService;

public class Librarian extends Person {
    private String password;
    private BorrowingService borrowingService;
    private MembershipService membershipService;

    public Librarian(String name, String password, BorrowingService borrowingService, MembershipService membershipService) {
        super(name);
        this.password = password;
        this.borrowingService = borrowingService;
        this.membershipService = membershipService;
    }

    public boolean verifyMember(Reader reader) {
        return membershipService.verifyMember(reader);
    }

    public boolean issueBook(Reader reader, Book book) {
        return borrowingService.borrowBook(reader, book);
    }

    public void returnBook(Reader reader, Book book) {
        borrowingService.returnBook(reader, book);
    }

    @Override
    public String whoYouAre() {
        return "Librarian: " + getName();
    }

    public boolean checkPassword(String enteredPassword) {
        return this.password.equals(enteredPassword);
    }
}