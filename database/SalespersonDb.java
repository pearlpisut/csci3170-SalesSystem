package database;

import java.sql.*;

public class SalespersonDb {

    private Db db;

    public SalespersonDb(Db db){
        this.db = db;
    }

    public void searchPartsByName(String name, int order) throws SQLException{
        Statement stmt = db.conn.createStatement();
        String query = String.format(
            "SELECT P.*, M.mName, C.cName from part P join manufacturer M on M.mID = P.mID " +
            "join category C on C.cID = P.cID where P.pName = '%s'"
            , name);
        ResultSet rs = stmt.executeQuery(query);
        System.out.println("| ID | Name | Manufacturer | Category | Quantity | Warranty | Price |");
	    while(rs.next()){
            int pid = rs.getInt(1);
            String pname = rs.getString(2);
            int price = rs.getInt(3);
            int quant = rs.getInt(4);
            int warrant = rs.getInt(5);
            String mName = rs.getString(8);
            String cName = rs.getString(9);
            System.out.println(
                String.format("| %d | %s | %s | %s | %d | %d | %d |\n", 
                pid, pname, mName, cName, quant, warrant, price)
            );
        }
        stmt.close();
        db.conn.close();
        rs.close();
    }
    public void searchPartsByManuName(String manuName, int order) throws SQLException{
        Statement stmt = db.conn.createStatement();
    }
    
}
