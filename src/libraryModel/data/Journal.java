package libraryModel.data;

import libraryModel.BookCategory;

public class Journal extends Book implements BookCategory {
    public Journal(String name, Author author, String edition) {
        super(name, author, edition);
    }

    @Override
    public String getCategoryName() {
        return "Journal";
    }
}

