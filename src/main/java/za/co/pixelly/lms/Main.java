package za.co.pixelly.lms;

import java.util.List;
import java.util.Scanner;

import za.co.pixelly.jdbc.DatabaseInitializer;
import za.co.pixelly.lms.model.Author;
import za.co.pixelly.lms.service.AuthorService;

public class Main {
    private static final AuthorService authorService = new AuthorService();
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
                case 6:
                    addAuthor();
                    break;
                case 7:
                    getAllAuthors();
                    break;
                case 8:
                    getAuthorById();
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
        System.out.println("3. Search Books by Title");
        System.out.println("4. Borrow Book");
        System.out.println("5. Return Book");
        System.out.println("6. Add Author");
        System.out.println("7. View All Authors");
        System.out.println("8. Search author by ID");
        System.out.println("9. Add Publisher");
        System.out.println("10. View All Publishers");
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
        // bookService.addBook(isbn, title, authorId, publisherId, yearPublished,
        // description);
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

}