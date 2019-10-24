package com.lambdaschool.vacationplanner.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lambdaschool.vacationplanner.logging.Loggable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Loggable
@Entity
@Table(name = "vacations")
public class Vacations extends Auditable
{
    // Fields
    // id, place
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long vacaid;

    @Column(nullable = false)
    private String place;

    // map one to many - todos
//    @OneToMany(mappedBy = "vacations",
//            cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("vacations")
//    private List<Todos> todos = new ArrayList<>();

    // map one to many - comments
//    @OneToMany(mappedBy = "vacations",
//            cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("vacations")
//    private List<Comments> comments = new ArrayList<>();

    // map many to one - user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnoreProperties({"vacation", "hibernateLazyInitializer"})
    private User user;



    // default constructor
    public Vacations()
    {
    }

    // constructors
    public Vacations(String place, User user)
    {
        this.place = place;
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

    public String getPlace()
    {
        return place;
    }

    public void setPlace(String place)
    {
        this.place = place;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // toString

    @Override
    public String toString() {
        return "Vacations{" +
                "vacaid=" + vacaid +
                ", place='" + place + '\'' +
                '}';
    }
}
