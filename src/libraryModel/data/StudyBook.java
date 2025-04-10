package libraryModel.data;

import libraryModel.BookCategory;

public class StudyBook extends Book implements BookCategory {
    public StudyBook(String name, Author author, String edition) {
        super(name, author, edition);
    }

    @Override
    public String getCategoryName() {
        return "Study Book";
    }
}
