package com.lambdaschool.vacaplanner.services;

import com.lambdaschool.vacaplanner.exceptions.ResourceNotFoundException;
import com.lambdaschool.vacaplanner.logging.Loggable;
import com.lambdaschool.vacaplanner.models.Todos;
import com.lambdaschool.vacaplanner.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Loggable
@Service(value = "todoService")
public class TodoServiceImpl implements TodoService
{
    @Autowired
    private TodoRepository todorepos;

    @Override
    public Todos findTodoById(long todoid) throws ResourceNotFoundException
    {
        return todorepos.findById(todoid)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Todo id " + todoid + " not found!"));
    }

    @Override
    public Todos findByTitle(String title)
    {
        Todos td = todorepos.findTodosByTitle(title.toLowerCase());
        if (td == null)
        {
            throw new ResourceNotFoundException(
                    "Todo title " + title + " not found!");
        }
        return td;
    }

    @Override
    public void delete(long todoid)
    {
        todorepos.findById(todoid)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Todo id " + todoid + " not found!"));
        todorepos.deleteById(todoid);
    }

    @Override
    public Todos save(Todos todos)
    {
        Todos newTodo = new Todos();
        newTodo.setTitle(todos.getTitle());
        newTodo.setDescription(todos.getDescription());

        return todorepos.save(newTodo);
    }

    @Override
    public Todos update(Todos todos, long todoid)
    {
        Todos currentTodo = new Todos();
        currentTodo.setTitle(todos.getTitle());
        currentTodo.setDescription(todos.getDescription());

        return todorepos.save(currentTodo);
    }
}
