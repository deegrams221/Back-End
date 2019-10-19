package com.lambdaschool.vacaplanner.models;

import com.lambdaschool.vacaplanner.logging.Loggable;

@Loggable // for custom swagger documentation - see SwaggerManualApiPlugin, ln 54
public class UserLogin
{
    private String name;
    private String password;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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
