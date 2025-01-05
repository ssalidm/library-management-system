package za.co.pixelly.lms;

import java.util.Scanner;

import za.co.pixelly.jdbc.DatabaseInitializer;
import za.co.pixelly.lms.service.AuthorService;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AuthorService authorService = new AuthorService();

        DatabaseInitializer.init();
        
        while (true) {
            showMenu();
            int choice = sc.nextInt();
            sc.nextLine();
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
        System.out.println("8. Add Publisher");
        System.out.println("9. View All Publishers");
        System.out.println("10. Exit");
        System.out.print("Enter your choice: ");
    }

}