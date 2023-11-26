package menu;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SalespersonMenu{
    public SalespersonMenu(){}
    public void searchInterface(int option){
        Scanner sc = new Scanner(System.in);
        if(option == 1){ // search for parts
            System.out.print("Choose the Search criterion:\n");
            System.out.print("1. Part Name\n2. Manufacturer Name\n");
            System.out.print("Choose the search criterion: ");
            int search_criterion = sc.nextInt();
            sc.nextLine();
            System.out.print("Type in the Search Keyword: ");
            String search_keyword = sc.nextLine();
            System.out.print("Choose ordering:\n");
            System.out.print("1. By price, ascending order\n2. By price, descending order\n");
            System.out.print("Choose the search criterion: ");
            int search_order = sc.nextInt();

            // perform query operations
        } else if(option == 2){ // sell a part
            System.out.print("Enter The Part ID: ");
            int part_id = sc.nextInt();
            System.out.print("Enter The Salesperson ID: ");
            int Salesperson_id = sc.nextInt();

            // perform query operations
            
        }
    } 
}