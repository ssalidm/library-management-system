package za.co.pixelly.lms.model;

import java.util.Objects;

public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public User(String firstName, String lastName, String email) throws Exception {
        validateName(email);
        validateEmail(email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User(int id, String firstName, String lastName, String email) throws Exception {
        this(firstName, lastName, email);
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return id == user.id && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName)
                && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", firstName='" + getFirstName() + "'" +
                ", lastName='" + getLastName() + "'" +
                ", email='" + getEmail() + "'" +
                "}";
    }

    private static void validateName(String name) throws Exception {
        if (name.isEmpty()) throw new Exception("First Name and Last Name cannot be empty.");
    }
    
    private static void validateEmail(String email) throws Exception {
        if (email.isEmpty()) throw new Exception("Email cannot be empty.");
    }

}
