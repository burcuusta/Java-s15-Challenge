package libraryModel.data;

import libraryModel.BookCategory;

public class StudyBook extends Book implements BookCategory {
    public StudyBook(int id,String name, Author author, String edition, String categoryName) {
        super(id, name, author, edition,categoryName);
    }

    @Override
    public String getCategoryName() {
        return "Study Book";
    }
}
