package libraryModel.services;

import libraryModel.data.MemberRecord;
import libraryModel.data.Reader;

import java.time.LocalDate;
import java.util.Map;

public class MembershipService {
    private Map<Reader, MemberRecord> memberRecords;

    public MembershipService(Map<Reader, MemberRecord> memberRecords) {
        this.memberRecords = memberRecords;
    }

    public boolean verifyMember(Reader reader) {
        return memberRecords.containsKey(reader);
    }

    public MemberRecord registerMember(Reader reader) {
        if (!memberRecords.containsKey(reader)) {
            MemberRecord newRecord = new MemberRecord(
                    generateMemberId(),
                    "Basic",
                    LocalDate.now(),
                    0,
                    5,
                    reader.getName(),
                    "",
                    ""
            );
            memberRecords.put(reader, newRecord);
            return newRecord;
        }
        return memberRecords.get(reader);
    }

    public MemberRecord getMemberRecord(Reader reader) {
        return memberRecords.get(reader);
    }

    private int generateMemberId() {
        return (int) (System.currentTimeMillis() % 1000);
    }



}
