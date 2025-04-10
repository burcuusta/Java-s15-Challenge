package libraryModel.data;

import libraryModel.BookCategory;

public class Magazine extends Book implements BookCategory {
    public Magazine(String name, Author author, String edition) {
        super(name, author, edition);
    }

    @Override
    public String getCategoryName() {
        return "Magazine";
    }
}
