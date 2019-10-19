package com.lambdaschool.vacaplanner.models;

import com.lambdaschool.vacaplanner.logging.Loggable;

import javax.persistence.*;

@Loggable
@Entity
@Table(name = "todos")
public class Todos
{
    // Fields
    // id, title, description
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long todoid;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    // default constructor
    public Todos()
    {
    }

    // constructor
    public Todos(String title, String description)
    {
        this.title = title;
        this.description = description;
    }

    // getters/setters
    public long getTodoid()
    {
        return todoid;
    }

    public void setTodoid(long todoid)
    {
        this.todoid = todoid;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    // toString

    @Override
    public String toString()
    {
        return "Todos{" +
                "todoid=" + todoid +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
