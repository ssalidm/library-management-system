package za.co.pixelly.lms.service;

import java.util.List;

import za.co.pixelly.lms.dao.AuthorDao;
import za.co.pixelly.lms.model.Author;

public class AuthorService {
    private final AuthorDao authorDao;

    public AuthorService() {
        this.authorDao = new AuthorDao();
    }

    @SuppressWarnings("unused")
    private void saveAuthor(String name) {
        Author author = new Author(name);
        authorDao.saveAuthor(author);
        System.out.println("Author added successfully.");
    }

    public List<Author> getAllAuthors() {
        return authorDao.getAllAuthors();
    }

}
