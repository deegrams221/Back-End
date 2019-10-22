package com.lambdaschool.vacationplanner.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lambdaschool.vacationplanner.logging.Loggable;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Loggable
@Entity
@Table(name = "vacations")
public class Vacations extends Auditable
{
    // Fields
    // id, place, date, todos
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long vacaid;

    private String vacation;

    @Column(nullable = false)
    private String place;

    @Transient
    private String pattern = "yyyy-MM-dd";

    @Transient
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    @Column
    private String date = simpleDateFormat.format(new Date());

    // map one to many - todos
    @OneToMany(mappedBy = "vacations",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("vacations")
    private List<Todos> todos = new ArrayList<>();

    // map one to many - comments
    @OneToMany(mappedBy = "vacations",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("vacations")
    private List<Comments> comments = new ArrayList<>();

    // map many to one - user
    @ManyToOne
    @JoinColumn(name = "userid",
            nullable = false)
    @JsonIgnoreProperties({"vacations"})
    private User user;

    // default constructor
    public Vacations()
    {
    }

    // constructors
    public Vacations(String place, String date)
    {
        this.place = place;
        this.date = date;
    }
    public Vacations(String place, String date, User user)
    {
        this.place = place;
        this.date = date;
        this.user = user;
    }

    // getters/setters
    public long getVacaid()
    {
        return vacaid;
    }

    public void setVacaid(long vacaid)
    {
        this.vacaid = vacaid;
    }

    public String getVacation()
    {
        return vacation;
    }

    public void setVacation(String vacation)
    {
        this.vacation = vacation;
    }

    public String getPlace()
    {
        return place;
    }

    public void setPlace(String place)
    {
        this.place = place;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    // toString
    @Override
    public String toString() {
        return "Vacations{" +
                "vacaid=" + vacaid +
                ", place='" + place + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
