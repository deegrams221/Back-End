package com.lambdaschool.vacaplanner.services;

import com.lambdaschool.vacaplanner.models.Todos;

public interface TodoService
{
    Todos findTodoById(long id);

    Todos findByTitle(String title);

    void delete(long id);

    Todos save(Todos todos);

    Todos update(Todos todos, long id);
}
