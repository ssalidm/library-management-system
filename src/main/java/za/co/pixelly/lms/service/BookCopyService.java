package za.co.pixelly.lms.service;

import za.co.pixelly.lms.dao.BookCopyDao;
import za.co.pixelly.lms.model.BookCopy;

public class BookCopyService {
    private final BookCopyDao bookCopyDao;

    public BookCopyService() {
        this.bookCopyDao = new BookCopyDao();
    }

    public void saveCopy(String isbn) {
        bookCopyDao.saveCopy(new BookCopy(isbn));
        System.out.println("Book copy saved successfully!.");
    }
}
