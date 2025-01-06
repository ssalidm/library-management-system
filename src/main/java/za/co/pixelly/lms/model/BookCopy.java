package za.co.pixelly.lms.model;

public class BookCopy {
    private int id;
    private String isbn;
    private boolean isAvailable;

    public BookCopy(String isbn) {
        this.isbn = isbn;
        this.isAvailable = true;
    }

    public BookCopy(int id, String isbn, boolean isAvailable) {
        this(isbn);
        this.id = id;
        this.isAvailable = isAvailable;
    }

    public int id() {
        return id;
    }

    public void id(int id) {
        this.id = id;
    }

    public String isbn() {
        return isbn;
    }

    public void isbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void isAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "BookCopy {" +
            " id='" + id() + "'," +
            " isbn='" + isbn() + "'," +
            " isAvailable='" + isAvailable() + "'" +
            " }";
    }


}
