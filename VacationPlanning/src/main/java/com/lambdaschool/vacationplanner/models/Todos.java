package com.lambdaschool.vacationplanner.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lambdaschool.vacationplanner.logging.Loggable;

import javax.persistence.*;

@Loggable
@Entity
@Table(name = "todos")
public class Todos extends Auditable
{
    // Fields
    // id, title, description
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long todoid;

    private String todos;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    // many to one - vacations
//    @ManyToOne
//    @JoinColumn(name = "vacaid",
//            nullable = false)
//    @JsonIgnoreProperties("todos")
//    private Vacations vacations = new Vacations();

    // map many to one - user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties({"todos", "hibernateLazyInitializer"})
    private User user;

    // default constructor
    public Todos()
    {
    }

    // constructor
    public Todos(String title, String description, User user)
    {
        this.title = title;
        this.description = description;
        this.user = user;
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

    public String getTodo() { return todos; }

    public void setTodo() { this.todos = todos; }

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

    public String getTodos() {
        return todos;
    }

    public void setTodos(String todos) {
        this.todos = todos;
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
        return "Todos{" +
                "todoid=" + todoid +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
