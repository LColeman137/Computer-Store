package edu.slcc.asdv.pojos;
/**
 *
 * @author 13378
 */
public class User
{
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNum;
    private String address;
    private String email;
    private String username;

    /**
     * The parameter constructor for user.
     * @param password The user password
     * @param email The user email
     * @param username The user username
     */
    public User(String password, String email, String username)
    {
        this.password = password;
        this.email = email;
        this.username = username;
    }

    /**
     * The empty constructor for user
     */
    public User(){}

    /**
     * Get the value of username
     *
     * @return the value of username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * Set the value of username
     *
     * @param username new value of username
     */
    public void setUsername(String username)
    {
        this.username = username;
    }  
    
    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * Get the value of address
     *
     * @return the value of address
     */
    public String getAddress()
    {
        return address;
    }

    /**
     * Set the value of address
     *
     * @param address new value of address
     */
    public void setAddress(String address)
    {
        this.address = address;
    }

    /**
     * Get the value of phoneNum
     *
     * @return the value of phoneNum
     */
    public String getPhoneNum()
    {
        return phoneNum;
    }

    /**
     * Set the value of phoneNum
     *
     * @param phoneNum new value of phoneNum
     */
    public void setPhoneNum(String phoneNum)
    {
        this.phoneNum = phoneNum;
    }

    /**
     * Get the value of lastName
     *
     * @return the value of lastName
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * Set the value of lastName
     *
     * @param lastName new value of lastName
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

      /**
     * Get the value of firstName
     *
     * @return the value of firstName
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Set the value of firstName
     *
     * @param firstName new value of firstName
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password)
    {
        this.password = password;
    }
}
