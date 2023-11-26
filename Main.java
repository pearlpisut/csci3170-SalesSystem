import menu.*;
import database.*;
import java.sql.*;

public class Main{
    public static void main(String[] args) throws SQLException{
        System.out.print("Enter the application \n");
	    Db db = new Db();
        try{
            db.getConnection();
        } catch(Exception x){
            System.err.println(x);
        }
        SalespersonMenu spm = new SalespersonMenu(db);
        spm.spInterface();
	System.out.print("\nApplication is done\n");
    }
}
