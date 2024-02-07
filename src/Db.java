import java.sql.Statement;
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
        //Open Connection
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            String sql = "CREATE DATABASE STUDENTS";
            stmt.executeUpdate(sql);
            System.out.println("Database created sucessfully");
        } catch (SQLException e) {
            System.out.println("Database already exists");
        }
    } 

    public static void createTable(int schoolYear) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement();
        ) {
            String sql = "USE STUDENTS";
            stmt.executeUpdate(sql);
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
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement();
        ) {
            String sql = "USE STUDENTS";
            stmt.executeUpdate(sql);
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
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement();
        ) {
            String sql = "USE STUDENTS";
            stmt.executeUpdate(sql);
            ResultSet rs;
            sql = "SELECT * FROM YEAR" + schoolYear;
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
    
}
