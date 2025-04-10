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


    public MemberRecord(int memberId, String type, LocalDate dateOfMembership, int noBooksIssued, int maxBookLimit, String name, String address, String phoneNo) {
        this.memberId = memberId;
        this.type = type;
        this.dateOfMembership = dateOfMembership;
        this.noBooksIssued = noBooksIssued;
        this.maxBookLimit = maxBookLimit;
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
    }

    public void incBookIssued() {
        this.noBooksIssued++;
    }


    public void decBookIssued() {
        if (this.noBooksIssued > 0) {
            this.noBooksIssued--;
        }
    }

    public String getMemberName() {
        return name;
    }

    public int getMaxBookLimit() {
        return maxBookLimit;
    }

    public int getNoBooksIssued() {
        return noBooksIssued;
    }

    public LocalDate getDateOfMembership() {
        return dateOfMembership;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getType() {
        return type;
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

    public void setNoBooksIssued(int noBooksIssued) {
        this.noBooksIssued = noBooksIssued;
    }

    public void setMaxBookLimit(int maxBookLimit) {
        this.maxBookLimit = maxBookLimit;
    }
}
