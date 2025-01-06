package za.co.pixelly.lms.model;

public class Publisher {
    private int id;
    private String name;

    public Publisher(String name) {
        this.name = name;
    }

    public Publisher(int id, String name) {
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
        return "Publisher {" +
                " id='" + id() + "'," +
                " name='" + name() + "'" +
                " }";
    }
}
