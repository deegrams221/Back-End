package com.lambdaschool.vacationplanner.models;

import com.lambdaschool.vacationplanner.logging.Loggable;

import javax.persistence.*;

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

    // default constructor
    public Comments()
    {
    }

    // constructor
    public Comments(String detail)
    {
        this.detail = detail;
    }

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
