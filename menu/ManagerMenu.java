package menu;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import database.*;

public class ManagerMenu {
    
    private Db db;

    public ManagerMenu(Db db){
        this.db = db;
    }

    public void mngInterace(){
        Scanner sc = new Scanner(System.in);
        // add db handler
        while(true){
            System.out.print("What kinds of operations would you like to perform?\n");
            System.out.print("1. List all salespersons\n2. Count the no. of sales record of each salesperson under a specific range on years of experience\n");
            System.out.print("3. Show the total sales value of each manufacturer\n4. Show the N most popular part\n5. Return to the main menu\n");
            System.out.print("Enter Your Choice: ");
            int option = sc.nextInt();
            System.out.print("\n");
            if(option == 1){
                System.out.print("Choose ordering:\n");
                System.out.print("1. By ascending order\n2. By descending order\n");
                int search_order = sc.nextInt();
                // execute query
            } else if(option == 2){
                System.out.print("Type in the lower bound for years of experience: ");
                int lower_bound = sc.nextInt();
                System.out.print("\nType in the upper bound for years of experience: ");
                int upper_bound = sc.nextInt();
                System.out.print("Transaction Record: \n");
                // execute query
            } else if(option == 3){
                // execute query
            } else if(option == 4){
                System.out.print("Type in the number of parts: ");
                int parts = sc.nextInt();
                // execute query
            } else if(option == 5){
                return;
            } else {
                System.out.println("Invalid option!");
            }
        }
    }
}
