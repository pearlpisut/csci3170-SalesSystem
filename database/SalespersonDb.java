package database;

import java.sql.*;
import java.util.Calendar;

public class SalespersonDb {

    private Db db;

    public SalespersonDb(Db db){
        this.db = db;
    }

    public void searchParts(String name, int search_order, int search_criterion) throws SQLException{
        Statement stmt = db.conn.createStatement();
        String search_parameter = "";
        String order = "";
        if(search_order == 1) {
            order = "ASC";
        } else {
            order = "DESC";
        }
        if(search_criterion == 1){
            search_parameter = "P.pName";
        } else {
            search_parameter = "M.mName";
        }
        String query = "SELECT P.*, M.mName, C.cName from part P join manufacturer M on M.mID = P.mID join category C on C.cID = P.cID where " + search_parameter + " LIKE '%" + name + "%' order by P.pPrice " + order;
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
                String.format("| %d | %s | %s | %s | %d | %d | %d |", 
                pid, pname, mName, cName, quant, warrant, price)
            );
        }
    }

    public void sellParts(int part_id, int salesperson_id) throws SQLException{
        // update quantity
        Statement stmt = db.conn.createStatement();
        String query_enquire_quantity = String.format("SELECT P.pAvailableQuantity, P.pName from part P where P.pID = %d", part_id);
        ResultSet rs = stmt.executeQuery(query_enquire_quantity);
        int quant = 0, transaction_count = 0;
        if(rs.next()) quant = rs.getInt(1);
        else return;
        String product_name = rs.getString(2);
        if(quant == 0){
            System.err.println("This item is out of stock!");
            return;
        }
        System.out.println(
            String.format("Product: %s(id: %d) Remaining Quantity: %d", product_name, part_id, quant-1)
        );
        String query_update_quantity = String.format("UPDATE part P SET P.pAvailableQuantity = P.pAvailableQuantity - 1 where P.pID = %d", part_id);
        stmt.executeUpdate(query_update_quantity);

        String query_count_transaction = String.format("SELECT COUNT(*) from transaction");
        rs = stmt.executeQuery(query_count_transaction);
        if(rs.next()) transaction_count = rs.getInt(1);

        String query_new_transaction = String.format("INSERT INTO transaction VALUES(?, ?, ?, ?)");
        java.sql.Date today = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        try {
            PreparedStatement stmtt = db.conn.prepareStatement(query_new_transaction);
            stmtt.setInt(1, transaction_count + 1);
            stmtt.setInt(2, part_id);
            stmtt.setInt(3, salesperson_id);
            stmtt.setDate(4, today);
            stmtt.execute();
        } catch (SQLException x) {
            System.out.println(x);
        }
    }
}
