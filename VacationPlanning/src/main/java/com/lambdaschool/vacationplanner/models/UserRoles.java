package com.lambdaschool.vacationplanner.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lambdaschool.vacationplanner.logging.Loggable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Loggable
@Entity
@Table(name = "userroles",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = {"userid", "roleid"})})
public class UserRoles extends Auditable implements Serializable
{
    // Fields
    @Id
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties("userroles")
    private User user;

    // map many to one - userroles
    @Id
    @ManyToOne
    @JoinColumn(name = "roleid")
    @JsonIgnoreProperties("userroles")
    private Role role;

    // default constructor
    public UserRoles()
    {
    }

    // constructor
    public UserRoles(User user,
                     Role role)
    {
        this.user = user;
        this.role = role;
    }

    // getters/setters
    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Role getRole()
    {
        return role;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }

    // methods
    // equals
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof UserRoles))
        {
            return false;
        }
        UserRoles userRoles = (UserRoles) o;
        return getUser().equals(userRoles.getUser()) && getRole().equals(userRoles.getRole());
    }

    // hashcode
    @Override
    public int hashCode()
    {
        return Objects.hash(getUser(),
                getRole());
    }

    // toString
    @Override
    public String toString()
    {
        return "UserRoles{" +
                "user=" + user.getUserid() +
                ", role=" + role.getRoleid() +
                '}';
    }
}
