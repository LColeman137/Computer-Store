package edu.slcc.asdv.beans;

import edu.slcc.asdv.utilities.Database;
import edu.slcc.asdv.pojos.User;
import edu.slcc.asdv.utilities.DESUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.SortedMap;

/**
 *
 * @author 13378
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable
{
    private User user = new User(); //Object for each user's info
    private SortedMap[] allUsers; // All users in the database

    /**
     * Adds the user information from the database to allUsers.
     * @throws SQLException
     */
    public UserBean() throws SQLException
    {
        allUsers = Database.getUsers().getRows();       
    }

    /**
     * Getter for user
     * @return user
     */
    public User getUser()
    {
        return user;
    }

    /**
     * Getter for allUsers
     * @return allUsers
     */
    public SortedMap[] getAllUsers()
    {
        return allUsers;
    }

    /**
     * Uses input information from the user to add them to the database and 
     * navigates them to a welcome page.
     * @param password
     * @param email
     * @param username
     * @return welcomePage_1
     * @throws SQLException
     */
    public String addUser(String password, String email, String username) throws SQLException
    {
        //>Encrypt the password so it can be compared to the encrypted database password
        password = DESUtil.encrypt(password, DESUtil.DES_KEY);
        //>Set the email, username, and password to the ones input by the user.
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        //>Add the user to the database
        Database.addUser(password, email, username);
        //>Navigate to the welcome page
        return "welcomePage_1";
    }

    /**
     * Verifies that this information matches an existing user in the database.
     * Navigates the user to a welcome page if the information is correct, or
     * to a new user page if the information is not found in the database.
     * @param username
     * @param password
     * @return welcomePage or newUserForm
     */
    public String verifyUser(String username, String password)
    {
        password = DESUtil.encrypt(password, DESUtil.DES_KEY);

        for (SortedMap allUser : allUsers)
        {
            if (allUser.get("username").equals(username) && allUser.get("password").equals(password))
            {
                user.setEmail((String) allUser.get("email"));
                user.setPassword((String) allUser.get("password"));
                user.setUsername((String) allUser.get("username"));
                return "welcomePage";
            }
        }
        return "newUserForm";
    }
}
