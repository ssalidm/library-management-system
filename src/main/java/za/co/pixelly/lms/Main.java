package za.co.pixelly.lms;

import java.util.List;
import java.util.Scanner;

import za.co.pixelly.jdbc.DatabaseInitializer;
import za.co.pixelly.lms.model.Author;
import za.co.pixelly.lms.model.Book;
import za.co.pixelly.lms.model.Publisher;
import za.co.pixelly.lms.service.AuthorService;
import za.co.pixelly.lms.service.BookCopyService;
import za.co.pixelly.lms.service.BookService;
import za.co.pixelly.lms.service.PublisherService;

public class Main {
    private static final AuthorService authorService = new AuthorService();
    private static final PublisherService publisherService = new PublisherService();
    private static final BookService bookService = new BookService();
    private static final BookCopyService bookCopyService = new BookCopyService();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        DatabaseInitializer.init();

        while (true) {
            showMenu();
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    getAllBooks();
                    break;
                case 6:
                    addAuthor();
                    break;
                case 7:
                    getAllAuthors();
                    break;
                case 8:
                    getAuthorById();
                    break;
                case 9:
                    addPublisher();
                    break;
                case 10:
                    getAllPublishers();
                    break;
                case 11:
                    addCopy();
                    break;
                case 0:
                    System.out.println("Goodbye! 👋🏾");
                    sc.close();
                    return;
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n===== Library Management System =====");
        System.out.println("1. Add Book");
        System.out.println("2. View All Books");
        System.out.println("3. Search Books");
        System.out.println("4. Borrow Book");
        System.out.println("5. Return Book");
        System.out.println("6. Add Author");
        System.out.println("7. View All Authors");
        System.out.println("8. Search author by ID");
        System.out.println("9. Add Publisher");
        System.out.println("10. View All Publishers");
        System.out.println("11. Add Book Copy");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addBook() {
        System.out.print("Enter ISBN: ");
        String isbn = sc.nextLine();
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author ID: ");
        int authorId = sc.nextInt();
        System.out.print("Enter Publisher ID: ");
        int publisherId = sc.nextInt();
        System.out.print("Enter Year Published: ");
        int yearPublished = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("Enter Description: ");
        String description = sc.nextLine();
        bookService.saveBook(isbn, title, authorId, publisherId, yearPublished,
                description);
    }

    private static void getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private static void addAuthor() {
        System.out.print("Enter Author Name: ");
        String authorName = sc.nextLine();
        authorService.saveAuthor(authorName);
    }

    private static void getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        if (authors.isEmpty()) {
            System.out.println("No authors found.");
        } else {
            authors.forEach(System.out::println);
        }
    }

    private static void getAuthorById() {
        System.out.print("Enter Author ID: ");
        int id = sc.nextInt();
        Author author = authorService.getAuthorById(id);
        if (author == null) {
            System.out.println("Author not found.");
        } else {
            System.out.println(author);
        }
    }

    private static void addPublisher() {
        System.out.print("Enter Publisher Name: ");
        String publisherName = sc.nextLine();
        publisherService.savePublisher(publisherName);
    }

    private static void getAllPublishers() {
        List<Publisher> publishers = publisherService.getAllPublishers();
        if (publishers.isEmpty()) {
            System.out.println("No publishers found.");
        } else {
            publishers.forEach(System.out::println);
        }
    }

    private static void addCopy() {
        System.out.print("Enter ISBN: ");
        String isbn = sc.nextLine();
        bookCopyService.saveCopy(isbn);
    }
}