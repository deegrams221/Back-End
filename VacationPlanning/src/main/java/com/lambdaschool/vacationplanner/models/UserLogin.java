package com.lambdaschool.vacationplanner.models;

import com.lambdaschool.vacationplanner.logging.Loggable;

@Loggable // for custom swagger documentation - see SwaggerManualApiPlugin, ln 54
public class UserLogin
{
    // Fields
    private String username;
    private String password;

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
}
