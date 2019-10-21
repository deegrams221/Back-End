package com.lambdaschool.vacationplanner.models;

import com.lambdaschool.vacationplanner.logging.Loggable;

// not an entiry - wont show up in database
@Loggable
public class UserMinimum // very min needed for creating a user
{
    // Fields
    private String username;
    private String password;
    private String email;

    // getters/setters
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
