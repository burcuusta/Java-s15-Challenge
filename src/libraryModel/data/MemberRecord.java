package libraryModel.data;

import java.time.LocalDate;

public class MemberRecord {
    private int memberId;
    private String type;
    private LocalDate dateOfMembership;
    private int noBooksIssued;
    private int maxBookLimit;
    private String name;
    private String address;
    private String phoneNo;
    private Reader member;

    public MemberRecord(int memberId, String type, LocalDate dateOfMembership, String name, String address, String phoneNo, Reader member) {
        this.memberId = memberId;
        this.type = type;
        this.dateOfMembership = dateOfMembership;
        this.noBooksIssued = 0;
        this.maxBookLimit = Reader.MAX_BOOK_LIMIT;
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
        this.member = member;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getType() {
        return type;
    }

    public LocalDate getDateOfMembership() {
        return dateOfMembership;
    }

    public int getNoBooksIssued() {
        return noBooksIssued;
    }

    public int getMaxBookLimit() {
        return maxBookLimit;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public Reader getMember() {
        return member;
    }

    public void incBookIssued() {
        this.noBooksIssued++;
    }

    public void decBookIssued() {
        if (this.noBooksIssued > 0) {
            this.noBooksIssued--;
        }
    }

    public void setMaxBookLimit(int maxBookLimit) {
        this.maxBookLimit = maxBookLimit;
    }
}
