package menu;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import database.*;

public class SalespersonMenu{

    private Db db;

    public SalespersonMenu(Db db){
        this.db = db;
    }

    // call this function from MainMenu if user is a salesperson
    public void spInterface() throws SQLException{
        Scanner sc = new Scanner(System.in);
        SalespersonDb dbHandler = new SalespersonDb(db);
        while(true){
            System.out.print("What kinds of operations would you like to perform?\n");
            System.out.print("1. Search for parts\n2. Sell a part\n3. Return to the main menu\n");
            System.out.print("Enter Your Choice: ");
            int option = sc.nextInt();
            System.out.print("\n");
            if(option == 1){ // search for parts
                System.out.print("Choose the Search criterion:\n");
                System.out.print("1. Part Name\n2. Manufacturer Name\n");
                System.out.print("Choose the search criterion: ");
                int search_criterion = sc.nextInt();
                if(search_criterion != 1 && search_criterion != 2)
                    System.err.println("Invalid search criterion!");
                sc.nextLine();
                System.out.print("Type in the Search Keyword: ");
                String search_keyword = sc.nextLine();
                System.out.print("Choose ordering:\n");
                System.out.print("1. By price, ascending order\n2. By price, descending order\n");
                System.out.print("Choose the search criterion: ");
                int search_order = sc.nextInt();
    
                // perform query operations
                dbHandler.searchParts(search_keyword, search_order, search_criterion);
                
            } else if(option == 2){ // sell a part
                System.out.print("Enter The Part ID: ");
                int part_id = sc.nextInt();
                System.out.print("Enter The Salesperson ID: ");
                int salesperson_id = sc.nextInt();
    
                // perform query operations
                dbHandler.sellParts(part_id, salesperson_id);
            } else if(option == 3){
                return;
            }
        }
    } 
}
