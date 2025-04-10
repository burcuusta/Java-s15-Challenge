package libraryModel.services;

import libraryModel.data.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class MembershipService {
    private Map<Reader, MemberRecord> memberRecords;

    public MembershipService() {
        this.memberRecords = new HashMap<>();
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
                    reader.getName(),
                    "",
                    "",
                    reader
            );
            memberRecords.put(reader, newRecord);
            System.out.println("✅ " + reader.getName() + " adlı okuyucu sisteme üye olarak kaydedildi.");
            return newRecord;
        } else {
            System.out.println("⚠️ " + reader.getName() + " adlı okuyucu zaten üye.");
            return memberRecords.get(reader);
        }
    }

    public MemberRecord getMemberRecord(Reader reader) {
        return memberRecords.get(reader);
    }

    private int generateMemberId() {
        return (int) (System.currentTimeMillis() % 1000);
    }
}