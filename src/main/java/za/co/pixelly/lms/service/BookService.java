package za.co.pixelly.lms.service;

import java.util.List;

import za.co.pixelly.lms.dao.BookCopyDao;
import za.co.pixelly.lms.dao.BookDao;
import za.co.pixelly.lms.model.Book;

public class BookService {
    private final BookDao bookDao;
    private final BookCopyDao bookCopyDao;

    public BookService() {
        this.bookDao = new BookDao();
        this.bookCopyDao = new BookCopyDao();
    }

    public void saveBook(String isbn, String title, int author_id, int publisher_id, int year_published,
            String description) {
        Book book = new Book(isbn, title, author_id, publisher_id, year_published, description);
        bookDao.saveBook(book);
        System.out.println("Book saved successfully!.");
    }

    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    public Book getBookByIsbn(String isbn) {
        return bookDao.getBookByIsbn(isbn);
    }

    public int getTotalNumberOfBooks() {
        return bookDao.getTotalNumberOfBooks();
    }

    public int getNumberOfCopies(String isbn) {
        return bookCopyDao.getNumberOfCopies(isbn);
    }

    public int getAvailableCopies(String isbn) {
        return bookCopyDao.getAvailableCopies(isbn);
    }

}
