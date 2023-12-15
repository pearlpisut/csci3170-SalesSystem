# csci3170-SalesSystem
This is the course project *Sales System* for **CSCI3170 - Introduction to Database Systems** by `group22`. It provides interfaces for multiple types of users to perform queries to MySQL server via JDBC in Java.
## contributors
Munkhbileg Batdorj, 1155155853

Phutanate Pisutsin, 1155163440
## project structure
The interface of the main menu is implemented in **Main.java**, while those for specific operations are in `./menu`: **AdminMenu**, **SalespersonMenu**, **ManagerMenu** .java.

The JDBC and SQL queries implementation for specific types of users can be found in `./database`: **AdminDb**, **SalespersonDb**, **ManagerDb** .java. Connection to the MySQL server is handled in **Db.java**
## how to run
`Makefile` is provided to perform the following:

clear `.class` files:
```bash
make clear
```
compile the program with the given `.jar` file
```bash
make comp
```
run the program
```bash
make run
```
