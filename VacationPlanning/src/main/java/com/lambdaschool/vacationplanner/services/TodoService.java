package com.lambdaschool.vacationplanner.services;

import com.lambdaschool.vacationplanner.models.Todos;

import java.util.List;

public interface TodoService
{
    List<Todos> findAllTodos();

    Todos findTodoById(long todoid);

    Todos findByTitle(String title);

    void delete(long todoid);

    Todos save(Todos todos);

    Todos update(Todos todos, long todoid);

    void assignTodoToVacation(long todoid, long vacaid);
}