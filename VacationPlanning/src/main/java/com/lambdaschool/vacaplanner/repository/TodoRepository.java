package com.lambdaschool.vacaplanner.repository;


import com.lambdaschool.vacaplanner.models.Todos;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todos, Long>
{
    Todos findTodosByTitle(String title);
}
