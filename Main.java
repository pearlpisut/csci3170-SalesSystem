import java.io.IOException;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import menu.*;
import database.*;


public class Main {
	public Main() {}
	public static void main(String[] args) throws SQLException{
	    Db db = new Db();
        try{
            db.getConnection();
        } catch(Exception x){
            System.err.println(x);
        }
		System.out.println("Welcome to sales system!");
		while(true){
			System.out.println();
			System.out.println("-----Main menu-----");
			System.out.println("What kinds of operations would you like to perform?");
			System.out.println("1. Operations for administrator");
			System.out.println("2. Operations for salesperson");
			System.out.println("3. Operations for manager");
			System.out.println("4. Exit this program");
			System.out.print("Enter Your Choice: ");	
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			switch (choice) {
				case 1: AdminMenu admin = new AdminMenu(db); admin.adInterface(); break;
				case 2: SalespersonMenu sales = new SalespersonMenu(db); sales.spInterface(); break;
				case 3: ManagerMenu manager = new ManagerMenu(db); manager.mngInterface(); break;
				case 4: return;
				default: System.out.println("Illegal input"); break;
			}
		}
	}

}