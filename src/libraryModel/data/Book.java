package libraryModel.data;

public class Book {
    private static int counter = 0;
    private int id;
    private String name;
    private Author author;

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Book.counter = counter;
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

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    private String edition;
    private boolean available;
    private Reader owner;

    public Book(String name, Author author, String edition) {
        this.id = ++counter;
        this.name = name;
        this.author = author;
        this.edition = edition;
        this.available = true;
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
    public String toString() {
        return "Book #" + id + ": " + name + " by " + author.getName() +
                " | Available: " + available;
    }
}
