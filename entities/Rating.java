package entities;

public class Rating {
    private int bookId;
    private float rating;

    public Rating(int bookId, float rating) {
        this.bookId = bookId;
        this.rating = rating;
    }
}
