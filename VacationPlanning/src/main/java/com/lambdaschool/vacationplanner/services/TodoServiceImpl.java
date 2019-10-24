package com.lambdaschool.vacationplanner.services;

import com.lambdaschool.vacationplanner.exceptions.ResourceNotFoundException;
import com.lambdaschool.vacationplanner.logging.Loggable;
import com.lambdaschool.vacationplanner.models.Todos;
import com.lambdaschool.vacationplanner.models.User;
import com.lambdaschool.vacationplanner.models.Vacations;
import com.lambdaschool.vacationplanner.repository.TodoRepository;
import com.lambdaschool.vacationplanner.repository.UserRepository;
import com.lambdaschool.vacationplanner.repository.VacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Loggable
@Service(value = "todoService")
public class TodoServiceImpl implements TodoService
{
    @Autowired
    private TodoRepository todorepos;

    @Autowired
    private VacationRepository vacarepos;

    @Autowired
    private UserRepository userrepos;

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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userrepos.findByUsername(authentication.getName());

        Todos newTodo = new Todos();
        newTodo.setTitle(todos.getTitle());
        newTodo.setDescription(todos.getDescription());
        List<Todos> userTodo = currentUser.getTodos();

        userTodo.add(newTodo);
        currentUser.setTodos(userTodo);
        newTodo.setUser(currentUser);

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


    @Override
    public void assignTodoToVacation(long todoid, long vacaid) {
        Todos currentTodo = todorepos.findById(todoid)
                .orElseThrow(() -> new ResourceNotFoundException(Long.toString(todoid)));

        Vacations currentVacation = vacarepos.findById(vacaid)
                .orElseThrow(() -> new ResourceNotFoundException(Long.toString(vacaid)));

        todorepos.assignTodoToVacation(todoid, vacaid);
    }
}
