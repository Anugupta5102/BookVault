package entities;

public class Rating {
    private int bookId;
    private int userId; // Added userId to track individual ratings
    private float rating;

    public Rating() {}

    public Rating(int bookId, int userId, float rating) {
        this.bookId = bookId;
        this.userId = userId;
        this.rating = rating;
    }

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }
}
