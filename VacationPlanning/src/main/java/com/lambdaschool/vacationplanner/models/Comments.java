package com.lambdaschool.vacationplanner.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lambdaschool.vacationplanner.logging.Loggable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Loggable
@Entity
@Table(name = "comments")
public class Comments extends Auditable
{
    // Fields
    // id, user, detail
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long comid;

    @Column(nullable = false)
    private String detail;

    // map many to one - vacation
    @ManyToOne
    @JoinColumn(name = "vacaid",
            nullable = false)
    @JsonIgnoreProperties("comments")
    private Vacations vacations = new Vacations();

    // map many to one - user
    @ManyToOne
    @JoinColumn(name = "userid",
            nullable = false)
    @JsonIgnoreProperties("user")
    private User user = new User();

    // default constructor
    public Comments()
    {
    }

    // constructor
    public Comments(String detail, User user, Vacations vacations)
    {
        this.detail = detail;
        this.user = user;
        this.vacations = vacations;
    }

//    public Comments(String detail, int i, int i1) {
//        super();
//    }

    // getters/setters
    public long getComid()
    {
        return comid;
    }

    public void setComid(long comid)
    {
        this.comid = comid;
    }

    public String getDetail()
    {
        return detail;
    }

    public void setDetail(String detail)
    {
        this.detail = detail;
    }

    public Vacations getVacations() {
        return vacations;
    }

    public void setVacations(Vacations vacations) {
        this.vacations = vacations;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // toString
    @Override
    public String toString()
    {
        return "Comments{" +
                "comid=" + comid +
                ", detail='" + detail + '\'' +
                '}';
    }
}
