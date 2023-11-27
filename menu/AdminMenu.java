package menu;

import java.sql.*;
import database.*;
import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class AdminMenu {
    private Db db;

    public AdminMenu(Db db) {
        this.db = db;
    }

    public void adInterface() {
        Scanner reader = new Scanner(System.in);
        AdminDb adHandler = new AdminDb(db);

        while (true) {
            System.out.println("\n-----Operations for administrator menu-----");
            System.out.println("What kinds of operation would you like to perform?");
            System.out.println("1. Create all tables");
            System.out.println("2. Delete all tables");
            System.out.println("3. Load from datafile");
            System.out.println("4. Show content of a table");
            System.out.println("5. Return to the main menu");
            System.out.printf("Enter your choice: ");

            int userChoice = reader.nextInt();

            try {
                switch (userChoice) {
                    case 1:
                        adHandler.createTables();
                        break;
                    case 2:
                        adHandler.deleteTables();
                        break;
                    case 3:
                        System.out.printf("Type in the Source Data Folder Path: ");
                        adHandler.loadData(reader.next());
                        break;
                    case 4:
                        System.out.printf("Which table would you like to show: ");
                        adHandler.showTables(reader.next());
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
}