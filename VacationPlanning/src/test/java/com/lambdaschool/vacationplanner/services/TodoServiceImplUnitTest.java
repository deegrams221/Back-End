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
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

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
    private VacationService vacaService;

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
        assertEquals("Surfing", todoService.findTodoById(18).getTitle());
    }

    // ResourceNotFoundException
    @Test(expected = ResourceNotFoundException.class)
    public void findTodoByIdNotFound() {
        assertEquals("blah blah blah", todoService.findTodoById(10).getTitle());
    }

    // Todos findByTitle(String title);
    @Test
    public void findByTitle() {
        assertEquals("Fishing", todoService.findByTitle("Fishing").getTitle());
    }

    // ResourceNotFoundException
    @Test(expected = ResourceNotFoundException.class)
    public void findByTitleNotfound() {
        assertEquals("Surfing", todoService.findByTitle("blah blah blah").getTitle());
    }

    // void delete(long todoid);
    @Test
    public void D_delete() {
        todoService.delete(18);

    }

    // ResourceNotFoundException
    @Test(expected = ResourceNotFoundException.class)
    public void DA_notFoundDelete() {
        todoService.delete(100);
    }

    // Todos save(Todos todos, User user, long vacaid);
//    @Test
//    public void F_save()
//    {
//    ArrayList<Todos> todos = new ArrayList<>();
//    Todos t3 = new Todos("Run", "Lets go running!", vacaService.findVacationById(6), userService.findUserById(15));
//
//    Todos saveT3 = todoService.save(t3, userService.findUserById(15), 6);
//
//        System.out.println("*** DATA ***");
//        System.out.println(saveT3);
//        System.out.println("*** DATA ***");
//    }

    // Todos update(Todos todos, long todoid);
//    @Test
//    public void update()
//    {
//        ArrayList<Todos> todos = new ArrayList<>();
//        Todos t2 = new Todos("Fishing", "Blah blah blah", vacaService.findVacationById(10), userService.findUserById(9));
//    }

    // void assignTodoToVacation(long todoid, long vacaid);

}
