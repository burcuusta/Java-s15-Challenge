package libraryModel.data;

import libraryModel.BookCategory;

public class Book implements BookCategory {
    private int id;
    private String name;
    private Author author;
    private String edition;
    private boolean available;
    private Reader owner;
    private String categoryName;

    public Book(int id, String name, Author author, String edition, String categoryName) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.edition = edition;
        this.available = true;
        this.owner = null;
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Author getAuthor() {
        return author;
    }

    public String getEdition() {
        return edition;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Reader getOwner() {
        return owner;
    }

    public void setOwner(Reader owner) {
        this.owner = owner;
    }

    @Override
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Author: " + author.getName() + ", Edition: " + edition + ", Available: " + available;
    }
}
