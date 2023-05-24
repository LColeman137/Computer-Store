package edu.slcc.asdv.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;

/**
 *
 * @author 13378
 */
public class Database
{
    /**
     * Sells the inventory item by updating the database and subtracting 1 from
     * quantity in the given table.
     *
     * @param table The table to remove from
     * @param type The type of product to be removed
     * @throws SQLException
     */
    public static void updateInventory(String table, String type) throws SQLException
    {
        Connection con = connection2();
        if (con == null)
        {
            System.out.println("Cannot connect to database.");
        }
        try
        {
            Statement stmt = con.createStatement();
            System.out.println(stmt);
            stmt.execute("UPDATE " + table + " SET qty = qty - 1 "
                    + "WHERE id LIKE \"" + type + "\"");
        } finally
        {
            con.close();
        }
    }

    /**
     * Sells the inventory item by updating the database and removing the
     * product from the given table.
     *
     * @param table The table to remove from.
     * @param type The type of product to be removed
     * @throws SQLException
     */
    public static void soldOut(String table, String type) throws SQLException
    {
        Connection con = connection2();
        if (con == null)
        {
            System.out.println("Cannot connect to database.");

        }
        try
        {
            Statement stmt = con.createStatement();
            stmt.execute("DELETE FROM \"" + table
                    + "\" WHERE name LIKE \"" + type + "\"");

        } finally
        {
            con.close();
        }
    }

    /**
     * Adds a user's username, email, and encrypted password to the users database table.
     * @param password 
     * @param email
     * @param username
     * @throws SQLException
     */
    public static void addUser(String password, String email, String username) throws SQLException
    {
        Connection con = connection();
        if (con == null)
        {
            System.out.println("Cannot connect to database.");

        }
        try
        {
            Statement stmt = con.createStatement();
            stmt.execute("INSERT INTO users VALUES (\""
                    + password + "\", \"" + email + "\", \"" + username + "\"); ");
            System.out.println("all good!");

        } finally
        {
            con.close();
        }
    }

    /**
     * Retrieves the users from the users database table.
     * @return the result set of all users within the table.
     * @throws SQLException
     */
    public static Result getUsers() throws SQLException
    {
        Connection con = connection();
        if (con == null)
        {
            System.out.println("Cannot connect to database.");
            return null;
        }
        try
        {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM users ");
            return ResultSupport.toResult(result);
        } finally
        {
            con.close();
        }
    }

    /**
     *  The connection to the website database.
     * @return The connection if successfully connected, null otherwise.
     */
    private static Connection connection() //throws InstantiationException, IllegalAccessException
    {
        String databaseName = "website";
        String userName = "root";
        String password = "";
        String URL2 = "com.mysql.jdbc.Driver";
        //String URL2 = "com.mysql.cj.jdbc.Driver";
        Connection con = null;
        try
        {// Load Sun's jdbc driver
            Class.forName(URL2).newInstance();
            System.out.println("JDBC Driver loaded!");
        } catch (Exception e) // driver not found
        {
            System.err.println("Unable to load database driver");
            System.err.println("Details : " + e);
            return null;
        }
        String ip = "localhost"; //internet connection
        String url = "jdbc:mysql://" + ip + ":3306/" + databaseName;
        try
        {
            con = DriverManager.getConnection(url, userName, password);
            con.setReadOnly(false);
        } catch (Exception e)
        {
            System.err.println(e.toString());
            return null;
        }
        System.out.println("connection successfull");
        return con;
    }

    /**
     * The connection to the productsforsale database
     * @return The connection if successfully connected, null otherwise.
     */
    static Connection connection2() //throws InstantiationException, IllegalAccessException
    {
        String databaseName = "productsforsale";
        String userName = "root";
        String password = "";
        String URL2 = "com.mysql.jdbc.Driver";
        //String URL2 = "com.mysql.cj.jdbc.Driver";
        Connection con = null;
        try
        {// Load Sun's jdbc driver
            Class.forName(URL2).newInstance();
            System.out.println("JDBC Driver loaded!");
        } catch (Exception e) // driver not found
        {
            System.err.println("Unable to load database driver");
            System.err.println("Details : " + e);
            return null;
        }
        String ip = "localhost"; //internet connection
        String url = "jdbc:mysql://" + ip + ":3306/" + databaseName;
        try
        {
            con = DriverManager.getConnection(url, userName, password);
            con.setReadOnly(false);
        } catch (Exception e)
        {
            System.err.println(e.toString());
            return null;
        }
        System.out.println("connection successfull");
        return con;
    }
}
