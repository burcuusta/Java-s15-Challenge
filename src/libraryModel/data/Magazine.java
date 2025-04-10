package libraryModel.data;

import libraryModel.BookCategory;

public class Magazine extends Book implements BookCategory {
    public Magazine(int id,String name, Author author, String edition, String categoryName) {
        super(id, name, author, edition,categoryName);
    }

    @Override
    public String getCategoryName() {
        return "Magazine";
    }
}
