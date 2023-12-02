package database;

import java.sql.*;

public class ManagerDb {
    
    private Db db;

    public ManagerDb(Db db){
        this.db = db;
    }

    public void listSalesperson(int search_order) throws SQLException{
        Statement stmt = db.conn.createStatement();
        String order;
        if(search_order == 1) order = "ASC";
        else order = "DESC";
        String query = String.format(
            "SELECT S.sID, S.sName, S.sPhoneNumber, S.sExperience from salesperson S order by S.sExperience %s"
        , order);
        ResultSet rs = stmt.executeQuery(query);
        System.out.println("| ID | Name | Mobile Phone | Years of Experience |");
        while(rs.next()){
            int id = rs.getInt(1);
            String name = rs.getString(2);
            int phone_number = rs.getInt(3);
            int experience = rs.getInt(4);
            System.out.println(
                String.format("| %d | %s | %d | %d |", 
                id, name, phone_number, experience)
            );
        }
    }

    public void countByExperience(int lower_bound, int upper_bound) throws SQLException{
        Statement stmt = db.conn.createStatement();
        String query = String.format(
            "SELECT S.sID, S.sName, S.sExperience, COUNT(*) from salesperson S join transaction T on S.sID = T.sID " + 
            "where S.sExperience >= %d AND S.sExperience <= %d group by S.sID, S.sName, S.sExperience;"
            , lower_bound, upper_bound);
        ResultSet rs = stmt.executeQuery(query);
        System.out.println("| ID | Name | Years of Experience | Number of Transaction |");
        while(rs.next()){
            int id = rs.getInt(1);
            String name = rs.getString(2);
            int experience = rs.getInt(3);
            int transaction = rs.getInt(4);
            System.out.println(
                String.format("| %d | %s | %d | %d |", 
                id, name, experience, transaction)
            );
        }
    }

    public void showTotalSalesManu() throws SQLException{
        Statement stmt = db.conn.createStatement();
        String query = String.format(
            "SELECT M.mID, M.mName, SUM(P.pPrice) as totalVal from manufacturer M join part P on M.mID = P.mID join " + 
            "transaction T on T.pID = P.pID group by M.mID, M.mName order by totalVal DESC"
            );
        ResultSet rs = stmt.executeQuery(query);
        System.out.println("| Manufacturer ID | Manufacturer Name | Total Sales Value |");
        while(rs.next()){
            int id = rs.getInt(1);
            String name = rs.getString(2);
            int total_sales = rs.getInt(3);
            System.out.println(
                String.format("| %d | %s | %d |", 
                id, name, total_sales)
            );
        }
    }

    public void showPopularParts(int count) throws SQLException{
        Statement stmt = db.conn.createStatement();
        String query = String.format(
            "SELECT P.pID, P.pName, COUNT(*) as popularity from part P join transaction T on P.pID = T.pID group by P.pID, P.pName " + 
            "order by popularity DESC limit %d"
        , count);
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()){
            int id = rs.getInt(1);
            String name = rs.getString(2);
            int popularity = rs.getInt(3);
            System.out.println(
                String.format("| %d | %s | %d |",
                id, name, popularity)
            );
        }
    }
}
