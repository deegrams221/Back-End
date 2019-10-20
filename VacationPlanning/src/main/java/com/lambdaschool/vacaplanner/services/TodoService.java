package com.lambdaschool.vacaplanner.services;

import com.lambdaschool.vacaplanner.models.Todos;

public interface TodoService
{
    Todos findTodoById(long todoid);

    Todos findByTitle(String title);

    void delete(long todoid);

    Todos save(Todos todos);

    Todos update(Todos todos, long todoid);
}
