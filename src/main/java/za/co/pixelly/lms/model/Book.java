package za.co.pixelly.lms.model;

public class Book {
    private String isbn;
    private String title;
    private int authorId;
    private int publisherId;
    private int yearPublished;
    private String description;

    public Book(String isbn, String title, int authorId, int publisherId, int yearPublished) {
        this.isbn = isbn;
        this.title = title;
        this.authorId = authorId;
        this.publisherId = publisherId;
        this.yearPublished = yearPublished;
    }

    public Book(String isbn, String title, int authorId, int publisherId, int yearPublished, String description) {
        this(isbn, title, authorId, publisherId, yearPublished);
        this.description = description;
    }

    public String isbn() {
        return isbn;
    }

    public void isbn(String isbn) {
        this.isbn = isbn;
    }

    public String title() {
        return title;
    }

    public void title(String title) {
        this.title = title;
    }

    public int authorId() {
        return authorId;
    }

    public void authorId(int authorId) {
        this.authorId = authorId;
    }

    public int publisherId() {
        return publisherId;
    }

    public void publisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public int yearPublished() {
        return yearPublished;
    }

    public void yearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public String description() {
        return description;
    }

    public void description(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "{" +
                " isbn='" + isbn() + "'," +
                " title='" + title() + "'," +
                " authorId='" + authorId() + "'," +
                " publisherId='" + publisherId() + "'," +
                " yearPublished='" + yearPublished() + "'," +
                " description='" + description() + "'" +
                " }";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + authorId;
        result = prime * result + publisherId;
        result = prime * result + yearPublished;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Book other = (Book) obj;
        if (isbn == null) {
            if (other.isbn != null)
                return false;
        } else if (!isbn.equals(other.isbn))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (authorId != other.authorId)
            return false;
        if (publisherId != other.publisherId)
            return false;
        if (yearPublished != other.yearPublished)
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        return true;
    }

}
