package database;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.io.*;
import java.nio.file.*;
import java.util.Calendar;
import java.util.Scanner;

public class AdminDb {
    private Db db;

    private String[] tables = { "category", "manufacturer", "part", "salesperson", "transaction" };

    public AdminDb(Db db) {
        this.db = db;
    }

    public void createTables() throws SQLException {
        Statement stmt = this.db.conn.createStatement();
        String[] createTables = {
            "CREATE TABLE part (pID INTEGER(3) NOT NULL PRIMARY KEY, pName VARCHAR(20) NOT NULL, pPrice INTEGER(5) NOT NULL, pAvailableQuantity INTEGER(2) NOT NULL, pWarrantyPeriod INTEGER(2) NOT NULL, " +
                "cID INTEGER(2) NOT NULL, mID INTEGER(1) NOT NULL)",
            "CREATE TABLE category (cID INTEGER(1) NOT NULL PRIMARY KEY, cName VARCHAR(20) NOT NULL)",
            "CREATE TABLE manufacturer (mID INTEGER(2) NOT NULL PRIMARY KEY, mName VARCHAR(20) NOT NULL, mAddress VARCHAR(50) NOT NULL, mPhoneNumber INTEGER(8) NOT NULL)",
            "CREATE TABLE salesperson (sID INTEGER(2) NOT NULL PRIMARY KEY, sName VARCHAR(20) NOT NULL, sAddress VARCHAR(50) NOT NULL, sPhoneNumber INTEGER(8) NOT NULL, sExperience INTEGER(1) NOT NULL)",
            "CREATE TABLE transaction (tID INTEGER(4) NOT NULL PRIMARY KEY, pID INTEGER(3) NOT NULL, sID INTEGER(2) NOT NULL, tdate DATE NOT NULL)",
    };
        System.out.printf("Processing...");
        for (int i = 0; i < createTables.length; i++) {
            stmt.executeUpdate(createTables[i]);
        }
        System.out.println("Done! Database is initialized!");
    }

    public void deleteTables() throws SQLException {
        System.out.printf("Processing...");
        Statement stmt = this.db.conn.createStatement();
        stmt.executeUpdate("SET FOREIGN_KEY_CHECKS=0;");
        for (int i = 0; i < tables.length; i++) {
            String dropTables = "DROP TABLE " + tables[i];
            stmt.executeUpdate(dropTables);
        }
        System.out.println("Done! Database is removed!");
    }

    public void loadData(String folderPath) throws SQLException {
        try {
            System.out.println("Processing...");
            Path folder = Paths.get(folderPath);
            File[] listOfFiles = folder.toFile().listFiles();

            for (File file : listOfFiles) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    handleFile(fileName, file);
                }
            }

            System.out.println("Done! Data is inputted to the database!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void handleFile(String fileName, File file) {
        try {
            switch (fileName) {
                case "category.txt":
                    handleCategoryFile(file);
                    break;
                case "manufacturer.txt":
                    handleManufacturerFile(file);
                    break;
                case "part.txt":
                    handlePartFile(file);
                    break;
                case "salesperson.txt":
                    handleSalespersonFile(file);
                    break;
                case "transaction.txt":
                    handleTransactionFile(file);
                    break;
                default:
                    System.out.println("Unknown file: " + fileName);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private void handleCategoryFile(File catergoryFile) {
        try {
            FileInputStream fstream = new FileInputStream(catergoryFile);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;

            while ((strLine = br.readLine()) != null) {
                String[] parseStr = strLine.split("\t");
                int cid = Integer.parseInt(parseStr[0]);
                String cname = parseStr[1];

                try {
                    PreparedStatement stmt = this.db.conn
                            .prepareStatement("INSERT INTO category (cID, cName) VALUES(?, ?)");
                    stmt.setInt(1, cid);
                    stmt.setString(2, cname);
                    stmt.execute();
                } catch (SQLException sql_e) {
                    System.out.println(sql_e);
                }
            }

            in.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void handleManufacturerFile(File manufactureFile) {
        try {
            FileInputStream fstream = new FileInputStream(manufactureFile);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;

            while ((strLine = br.readLine()) != null) {
                String[] parseStr = strLine.split("\t");
                int mid = Integer.parseInt(parseStr[0]);
                String mname = parseStr[1];
                String maddress = parseStr[2];
                int mphonenumber = Integer.parseInt(parseStr[3]);

                try {
                    PreparedStatement stmt = this.db.conn.prepareStatement(
                            "INSERT INTO manufacturer (mID, mName, mAddress, mPhoneNumber) VALUES(?, ?, ?, ?)");
                    stmt.setInt(1, mid);
                    stmt.setString(2, mname);
                    stmt.setString(3, maddress);
                    stmt.setInt(4, mphonenumber);
                    stmt.execute();
                } catch (SQLException sql_e) {
                    System.out.println(sql_e);
                }
            }
            in.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private void handlePartFile(File partFile) {
        try {
            FileInputStream fstream = new FileInputStream(partFile);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
    
            while ((strLine = br.readLine()) != null) {
                String[] parseStr = strLine.split("\t");
                int pid = Integer.parseInt(parseStr[0]);
                String pname = parseStr[1];
                int pprice = Integer.parseInt(parseStr[2]);
                int mid = Integer.parseInt(parseStr[3]);
                int cid = Integer.parseInt(parseStr[4]);
                int pwarrantyperiod = Integer.parseInt(parseStr[5]);
                int pavailablequantity = Integer.parseInt(parseStr[6]);
    
                try {
                    PreparedStatement stmt = this.db.conn.prepareStatement(
                            "INSERT INTO part (pID, pName, pPrice, mID, cID, pWarrantyPeriod, pAvailableQuantity) VALUES(?, ?, ?, ?, ?, ?, ?)");
                    stmt.setInt(1, pid);
                    stmt.setString(2, pname);
                    stmt.setInt(3, pprice);
                    stmt.setInt(4, mid);
                    stmt.setInt(5, cid);
                    stmt.setInt(6, pwarrantyperiod);
                    stmt.setInt(7, pavailablequantity);
                    stmt.execute();
                } catch (SQLException sql_e) {
                    System.out.println(sql_e);
                }
            }
            in.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void handleSalespersonFile(File salesFile) {
        try {
            FileInputStream fstream = new FileInputStream(salesFile);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;

            while ((strLine = br.readLine()) != null) {
                String[] parseStr = strLine.split("\t");
                int sid = Integer.parseInt(parseStr[0]);
                String sname = parseStr[1];
                String saddress = parseStr[2];
                int sphonenumber = Integer.parseInt(parseStr[3]);
                int sexperience = Integer.parseInt(parseStr[4]);

                try {
                    PreparedStatement stmt = this.db.conn.prepareStatement(
                            "INSERT INTO salesperson (sID, sName, sAddress, sPhoneNumber, sExperience) VALUES(?, ?, ?, ?, ?)");
                    stmt.setInt(1, sid);
                    stmt.setString(2, sname);
                    stmt.setString(3, saddress);
                    stmt.setInt(4, sphonenumber);
                    stmt.setInt(5, sexperience);
                    stmt.execute();
                } catch (SQLException sql_e) {
                    System.out.println(sql_e);
                }
            }
            in.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void handleTransactionFile(File transactionFile) {
        try {
            FileInputStream fstream = new FileInputStream(transactionFile);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;

            while ((strLine = br.readLine()) != null) {
                String[] parseStr = strLine.split("\t");
                int tid = Integer.parseInt(parseStr[0]);
                int pid = Integer.parseInt(parseStr[1]);
                int sid = Integer.parseInt(parseStr[2]);
                String tdateString = parseStr[3];

                String[] transactionData = tdateString.split("/");
                Calendar tdate = Calendar.getInstance();
                tdate.set(Integer.parseInt(transactionData[2]), Integer.parseInt(transactionData[1]),
                        Integer.parseInt(transactionData[0]));
                long brev = tdate.getTimeInMillis();

                try {
                    PreparedStatement stmt = this.db.conn
                            .prepareStatement("INSERT INTO transaction (tID, pID, sID, tdate) VALUES(?, ?, ?, ?)");
                    stmt.setInt(1, tid);
                    stmt.setInt(2, pid);
                    stmt.setInt(3, sid);
                    stmt.setDate(4, new Date(brev));
                    stmt.execute();
                } catch (SQLException sql_e) {
                    System.out.println(sql_e);
                }
            }
            in.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void showTables(String tableName) throws SQLException {
        System.out.println("Content of table " + tableName + ":");
        switch (tableName) {
            case "category":
                printCategory(tableName);
                break;
            case "manufacturer":
                printManufacturer(tableName);
                break;
            case "part":
                printPart(tableName);
                break;
            case "salesperson":
                printSalesperson(tableName);
                break;
            case "transaction":
                printTransaction(tableName);
                break;
            default:
                System.out.println("Invalid table name");
                break;
        }
        System.out.println();
    }

    private void printCategory(String tableName) {
        System.out.println("| cID | cName |");
        try {
            Statement stmt = this.db.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT cID, cName FROM category");
            while (rs.next()) {
                int cid = rs.getInt(1);
                String cname = rs.getString(2);
                System.out.printf("| %d | %s |\n", cid, cname);
            }
        } catch (SQLException sql_e) {
            System.out.println(sql_e);
        }
    }

    private void printManufacturer(String tableName) {
        System.out.println("| mID | mName | mAddress | mPhoneNumber |");
        try {
            Statement stmt = this.db.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT mID, mName, mAddress, mPhoneNumber FROM manufacturer");
            while (rs.next()) {
                int mid = rs.getInt(1);
                String mname = rs.getString(2);
                String maddress = rs.getString(3);
                int mphonenumber = rs.getInt(4);
                System.out.printf("| %d | %s | %s | %d |\n", mid, mname, maddress, mphonenumber);
            }
        } catch (SQLException sql_e) {
            System.out.println(sql_e);
        }
    }

    private void printPart(String tableName) {
        System.out.println("| pID | pName | pPrice | mID | cID | pWarrantyPeriod | pAvailableQuantity |");
        try {
            Statement stmt = this.db.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT pID, pName, pPrice, mID, cID, pWarrantyPeriod, pAvailableQuantity FROM part");
            while (rs.next()) {
                int pid = rs.getInt(1);
                String pname = rs.getString(2);
                int pprice = rs.getInt(3);
                int mid = rs.getInt(4);
                int cid = rs.getInt(5);
                int pwarrantyperiod = rs.getInt(6);
                int pavailablequantity = rs.getInt(7);
    
                System.out.printf("| %d | %s | %d | %d | %d | %d | %d |\n", pid, pname, pprice, mid, cid, pwarrantyperiod, pavailablequantity);
            }
        } catch (SQLException sql_e) {
            System.out.println(sql_e);
        }
    }

    private void printSalesperson(String tableName) {
        System.out.println("| sID | sName | sAddress | sPhoneNumber | sExperience |");
        try {
            Statement stmt = this.db.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT sID, sName, sAddress, sPhoneNumber, sExperience FROM salesperson");
            while (rs.next()) {
                int sid = rs.getInt(1);
                String sname = rs.getString(2);
                String saddress = rs.getString(3);
                int sphonenumber = rs.getInt(4);
                int sexperience = rs.getInt(5);
                System.out.printf("| %d | %s | %s | %d | %d |\n", sid, sname, saddress, sphonenumber, sexperience);
            }
        } catch (SQLException sql_e) {
            System.out.println(sql_e);
        }
    }

    private void printTransaction(String tableName) {
        System.out.println("| tID | pID | sID | tDate |");
        try {
            Statement stmt = this.db.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT tID, pID, sID, tDate FROM transaction");
            while (rs.next()) {
                int tid = rs.getInt(1);
                int pid = rs.getInt(2);
                int sid = rs.getInt(3);
                Date tdate = rs.getDate(4);

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dateStr = sdf.format(tdate);

                System.out.printf("| %d | %d | %d | %s |\n", tid, pid, sid, dateStr);
            }
        } catch (SQLException sql_e) {
            System.out.println(sql_e);
        }
    }


}