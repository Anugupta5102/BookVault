package entities;

public class Book {
    private int bookId;
    private String title;
    private int authorId;
    private int languageId;
    private String genre;
    private float rating;
    private boolean available;

    public Book(int bookId, String title, int authorId, int languageId, String genre, float rating, boolean available) {
        this.bookId = bookId;
        this.title = title;
        this.authorId = authorId;
        this.languageId = languageId;
        this.genre = genre;
        this.rating = rating;
        this.available = available;
    }
}
