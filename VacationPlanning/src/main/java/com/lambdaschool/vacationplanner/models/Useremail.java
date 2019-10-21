package com.lambdaschool.vacationplanner.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lambdaschool.vacationplanner.logging.Loggable;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Loggable
@Entity
@Table(name = "useremails",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = {"userid", "useremail"})})
public class Useremail extends Auditable
{
    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long useremailid;

    @Column(nullable = false)
    @Email
    private String useremail;

    // map many to one - useremails
    @ManyToOne
    @JoinColumn(name = "userid",
            nullable = false)
    @JsonIgnoreProperties("useremails")

    private User user;

    // default constructor
    public Useremail()
    {
    }

    // constructor
    public Useremail(User user,
                     String useremail)
    {
        this.useremail = useremail;
        this.user = user;
    }

    // getters/setters
    public long getUseremailid()
    {
        return useremailid;
    }

    public void setUseremailid(long useremailid)
    {
        this.useremailid = useremailid;
    }

    public String getUseremail()
    {
        if (useremail == null) // this is possible when updating a user
        {
            return null;
        } else
        {
            return useremail.toLowerCase();
        }
    }

    public void setUseremail(String useremail)
    {
        this.useremail = useremail.toLowerCase();
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    // toString
    @Override
    public String toString() {
        return "Useremail{" +
                "useremailid=" + useremailid +
                ", useremail='" + useremail + '\'' +
                ", user=" + user +
                '}';
    }
}
