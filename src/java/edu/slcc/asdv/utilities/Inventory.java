package edu.slcc.asdv.utilities;

import static edu.slcc.asdv.utilities.Database.connection2;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;

/**
 *
 * @author 13378
 */
public class Inventory
{
    /**
     * Gets all products from the laptops database table.
     *
     * @return the result set of all laptops within the table.
     * @throws SQLException
     */
    public static Result getInventory() throws SQLException
    {
        Connection con = connection2();
        if (con == null)
        {
            System.out.println("Cannot connect to database.");
            return null;
        }
        try
        {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM inventory");
            return ResultSupport.toResult(result);
        } finally
        {
            con.close();
        }
    }
}
