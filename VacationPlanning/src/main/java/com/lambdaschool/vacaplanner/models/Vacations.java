package com.lambdaschool.vacaplanner.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lambdaschool.vacaplanner.logging.Loggable;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Loggable
@Entity
@Table(name = "vacations")
public class Vacations
{
    // Fields
    // id, place, date, todos
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long vacaid;

    @Column(nullable = false)
    private String place;

    @Column
    private String pattern = "yyyy-MM-dd";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    private String date = simpleDateFormat.format(new Date());

    // map one to many - todos
    @OneToMany(mappedBy = "vacations",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("vacations")
    private List<Todos> todos = new ArrayList<>();

    // default constructor
    public Vacations()
    {
    }

    // constructor
    public Vacations(String place, String date, List<Todos> todos)
    {
        this.place = place;
        this.date = date;
        this.todos = todos;
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

    public List<Todos> getTodos()
    {
        return todos;
    }

    public void setTodos(List<Todos> todos)
    {
        this.todos = todos;
    }

    // toString
    @Override
    public String toString()
    {
        return "Vacations{" +
                "vacaid=" + vacaid +
                ", place='" + place + '\'' +
                ", date='" + date + '\'' +
                ", todos=" + todos +
                '}';
    }
}
