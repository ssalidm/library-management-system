package za.co.pixelly.lms.service;

import java.util.List;

import za.co.pixelly.lms.dao.AuthorDao;
import za.co.pixelly.lms.model.Author;

public class AuthorService {
    private final AuthorDao authorDao;

    public AuthorService() {
        this.authorDao = new AuthorDao();
    }

    public void saveAuthor(String name) {
        Author author = new Author(name);
        authorDao.saveAuthor(author);
        System.out.println("Author '" + name + "' saved successfully!.");
    }

    public List<Author> getAllAuthors() {
        return authorDao.getAllAuthors();
    }

    public Author getAuthorById(int id) {
        return authorDao.getAuthorById(id);
    }

}
