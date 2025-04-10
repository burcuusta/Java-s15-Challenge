package libraryModel.data;

import libraryModel.BookCategory;

public class Journal extends Book implements BookCategory {
    public Journal(int id,String name, Author author, String edition, String categoryName) {
        super(id, name, author, edition,categoryName);
    }

    @Override
    public String getCategoryName() {
        return "Journal";
    }
}

