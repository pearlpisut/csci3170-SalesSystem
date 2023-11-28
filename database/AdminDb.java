package database;

import java.sql.*;
import java.io.*;
import java.nio.file.*;
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
                    "cID INTEGER(2) NOT NULL, mID INTEGER(1) NOT NULL, FOREIGN KEY (mID) REFERENCES manufacturer(mID), FOREIGN KEY (cID) REFERENCES category(cID))",
                "CREATE TABLE category (cID INTEGER(1) NOT NULL PRIMARY KEY, cName VARCHAR(20) NOT NULL)",
                "CREATE TABLE manufacturer (mID INTEGER(2) NOT NULL PRIMARY KEY, mName VARCHAR(20) NOT NULL, mAddress VARCHAR(50) NOT NULL, mPhoneNumber INTEGER(8) NOT NULL)",
                "CREATE TABLE salesperson (sID INTEGER(2) NOT NULL PRIMARY KEY, sName VARCHAR(20) NOT NULL, sAddress VARCHAR(50) NOT NULL, sPhoneNumber INTEGER(8) NOT NULL, sExperience INTEGER(1) NOT NULL)",
                "CREATE TABLE transaction (tID INTEGER(4) NOT NULL PRIMARY KEY, pID INTEGER(3) NOT NULL, sID INTEGER(2) NOT NULL, tdate DATE NOT NULL, FOREIGN KEY(pID) REFERENCES part(pID), FOREIGN KEY (sID) REFERENCES salesperson(sID))",
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
                /*if (fileName.startsWith("category")) {
                    CategoryHandler ch = new CategoryHandler(this.db);
                    ch.handleCategoryFile(file);
                } else if (fileName.startsWith("manufacturer")) {
                    ManufacturerHandler mh = new ManufacturerHandler(this.db);
                    mh.handleManufacturerFile(file);
                } else if (fileName.startsWith("part")) {
                    PartHandler ph = new PartHandler(this.db);
                    ph.handlePartFile(file);
                } else if (fileName.startsWith("salesperson")) {
                    SalespersonHandler sh = new SalespersonHandler(this.db);
                    sh.handleSalespersonFile(file);
                } else if (fileName.startsWith("transaction")) {
                    TransactionHandler th = new TransactionHandler(this.db);
                    th.handleTransactionFile(file);
                }*/
            }
        }
        System.out.println("Done! Data is inputted to the database!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void showTables(String tableName) throws SQLException {
        System.out.println("Content of table " + tableName + ":");
        /*switch (tableName) {
            case "category":
                new CategoryHandler(this.db).printCategory(tableName);
                break;
            case "manufacturer":
                new ManufacturerHandler(this.db).printManufacturer(tableName);
                break;
            case "part":
                new PartHandler(this.db).printPart(tableName);
                break;
            case "salesperson":
                new SalespersonHandler(this.db).printSalesperson(tableName);
                break;
            case "transaction":
                new TransactionHandler(this.db).printTransaction(tableName);
                break;
            default:
                System.out.println("Invalid table name");
                break;
        }*/
        System.out.println();
    }
}