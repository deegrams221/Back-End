package com.lambdaschool.vacationplanner.services;

import com.lambdaschool.vacationplanner.VacationplannerApplication;
import com.lambdaschool.vacationplanner.exceptions.ResourceNotFoundException;
import com.lambdaschool.vacationplanner.models.Todos;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VacationplannerApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class TodoServiceImplUnitTest
{
    @Autowired
    private TodoService todoService;

    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    // Tests
    // Todos findTodoById(long todoid);
    @Test
    public void findTodoById() {
        assertEquals("Surfing", todoService.findTodoById(21).getTitle());
    }

    // ResourceNotFoundException
    @Test(expected = ResourceNotFoundException.class)
    public void findTodoByIdNotFound() {
        assertEquals("blah blah blah", todoService.findTodoById(10).getTitle());
    }

    // ResourceNotFoundException
    @Test(expected = ResourceNotFoundException.class)
    public void findByTitleNotfound() {
        assertEquals("Surfing", todoService.findByTitle("blah blah blah").getTitle());
    }

    // void delete(long todoid);
    @Test
    public void D_delete() {
        todoService.delete(21);
    }

    // ResourceNotFoundException
    @Test(expected = ResourceNotFoundException.class)
    public void DA_notFoundDelete() {
        todoService.delete(100);
    }

    // Todos update(Todos todos, long todoid);
    @Test
    public void update()
    {
        Todos t2 = new Todos("Fishing", "Blah blah blah",  userService.findUserById(6));
    }
}
