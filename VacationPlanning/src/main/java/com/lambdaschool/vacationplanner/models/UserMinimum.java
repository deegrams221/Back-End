package com.lambdaschool.vacationplanner.models;

import com.lambdaschool.vacationplanner.logging.Loggable;

import javax.persistence.Entity;
import javax.persistence.Table;

// not an entity - wont show up in database
//@Entity
@Loggable
//@Table(name = "usermins")
public class UserMinimum // very min needed for creating a user
{
    // Fields
    private String username;
    private String password;
    private String email;

    // constructors


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
