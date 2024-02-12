import java.sql.Statement;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;


public class Db {
    static final String DB_URL = "jdbc:mysql://localhost/";
    static final String USER = "root";
    static final String PASS = "250410270317@Tm";

    public static void createDB() {
        //OPEN CONNECTION
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            //CREATE DB
            String sql = "CREATE DATABASE STUDENTS";
            stmt.executeUpdate(sql);
            System.out.println("Database created sucessfully");
        } catch (SQLException e) {
            System.out.println("Database already exists");
        }
    } 

    public static void createTable(int schoolYear) {
        //OPEN CONNECTION
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement();
        ) {
            //FIND DB
            String sql = "USE STUDENTS";
            stmt.executeUpdate(sql);

            //CREATE TABLE
            sql = "CREATE TABLE YEAR"+ schoolYear +" (id INT NOT NULL, name VARCHAR(30), PRIMARY KEY(id) )";
            stmt.executeUpdate(sql);
            System.out.println("Table created sucessfully");
        } catch (SQLSyntaxErrorException f) {
            System.out.println("Table already exists");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printAllTables() {
        //OPEN CONNECTION
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement();
        ) {
            //FIND DB
            String sql = "USE STUDENTS";
            stmt.executeUpdate(sql);

            //RESULTS
            ResultSet rs;
            for (int i=7; i<12; i++) {
                sql = "SELECT * FROM YEAR" + i;
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    System.out.printf("%s %s\n",id,name);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printTable(int schoolYear) {
        //OPEN CONNECTION
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement();
        ) {
            //SELECT
            String sql = "USE STUDENTS";
            stmt.executeUpdate(sql);
            sql = "SELECT * FROM YEAR" + schoolYear;
            
            //RESULTS
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.printf("%s %s\n",id,name);
            }
        } catch (SQLSyntaxErrorException f) {
            System.out.println("Table does not exist");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void findByID() {
        //OPEN CONNECTION
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement();
        ) {
            //INIT
            Scanner scanner = new Scanner(System.in);

            //USER INPUT
            System.out.println("Which year group would you like to search from?");
            int yearGroupQuery = scanner.nextInt();
            System.out.println("Which id would you like to select?");
            int idQuery = scanner.nextInt();

            //SELECT
            String sql = "USE STUDENTS";
            stmt.executeUpdate(sql);
            sql = "SELECT * FROM YEAR" + yearGroupQuery +" WHERE id=" + idQuery;

            //Results
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.printf("%s %s\n",id,name);
            }

            //CLOSE
            scanner.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    public static void findByName() {
        //OPEN CONNECTION
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement();
        ) {
            //INIT
            Scanner scanner = new Scanner(System.in);

            //USER INPUT
            System.out.println("Which year group would you like to search from?");
            int yearGroupQuery = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Which name would you like to select?");
            String nameQuery = scanner.nextLine();

            //SELECT
            String sql = "USE STUDENTS";
            stmt.executeUpdate(sql);
            sql = "SELECT * FROM YEAR" + yearGroupQuery +" WHERE name='" + nameQuery+"'";

            //Results
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.printf("%s %s\n",id,name);
            }

            //CLOSE
            scanner.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }
    
}
