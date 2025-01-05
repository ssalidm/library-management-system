package za.co.pixelly.lms.model;

public class Author {
    private int id;
    private String name;

    public Author(String name) {
        this.name = name;
    }

    public Author(int id, String name) {
        this(name);
        this.id = id;
    }

    public int id() {
        return this.id;
    }

    public String name() {
        return this.name;
    }

    public void name(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Author {" +
                " id='" + id() + "'" +
                ", name='" + name() + "'" +
                "}";
    }
}
