package com.lambdaschool.vacationplanner.services;

import com.lambdaschool.vacationplanner.VacationplannerApplication;
import com.lambdaschool.vacationplanner.exceptions.ResourceFoundException;
import com.lambdaschool.vacationplanner.exceptions.ResourceNotFoundException;
import com.lambdaschool.vacationplanner.models.Role;
import com.lambdaschool.vacationplanner.models.User;
import com.lambdaschool.vacationplanner.models.UserRoles;
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
public class UserServiceImplUnitTest
{
    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception
    {}

    // Tests
    // UserDetails loadUserByUsername(String username);
    @Test
    public void A_loadUserByUsername()
    {
        assertEquals("admin", userService.loadUserByUsername("admin").getUsername());
    }

    // User findUserById(long id);
    @Test
    public void B_findUserById()
    {
        assertEquals("admin", userService.findUserById(4).getUsername());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void BA_findUserByIdNotFound()
    {
        assertEquals("admin", userService.findUserById(10).getUsername());
    }

    // List<User> findAll(Pageable pageable);
    @Test
    public void C_findAll()
    {
        assertEquals(6, userService.findAll(Pageable.unpaged()).size());
    }

    // void delete(long id);
    @Test
    public void D_delete()
    {
        userService.delete(15);
        assertEquals(5, userService.findAll(Pageable.unpaged()).size());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void DA_notFoundDelete()
    {
        userService.delete(100);
        assertEquals(4, userService.findAll(Pageable.unpaged()).size());
    }

    // User findByUsername(String username);
    @Test
    public void E_findByUsername()
    {
        assertEquals("admin", userService.findByName("admin").getUsername());
    }

    @Test (expected = ResourceNotFoundException.class)
    public void AA_findByUsernameNotfound()
    {
        assertEquals("admin", userService.findByName("turtle").getUsername());
    }

    // List<User> findByNameContaining(String username,
    //                                    Pageable pageable);
    @Test
    public void AB_findByNameContaining()
    {
        assertEquals(3, userService.findByNameContaining("a", Pageable.unpaged()).size());
    }

    // User save(User user);
    @Test
    public void F_save()
    {
        ArrayList<UserRoles> user = new ArrayList<>();
        User u2 = new User("Tidus", "mypassword!", "tidus@lambda.schoola", user);

        User saveU2 = userService.save(u2);

        System.out.println("*** DATA ***");
        System.out.println(saveU2);
        System.out.println("*** DATA ***");
    }

    @Test (expected = ResourceFoundException.class)
    public void FA_saveResourceFound()
    {
        ArrayList<UserRoles> user = new ArrayList<>();
        User u2 = new User("Vivi", "ILuvWebDev!", "vivi@lambda.school", user);

        User saveU2 = userService.save(u2);

        System.out.println("*** DATA ***");
        System.out.println(saveU2);
        System.out.println("*** DATA ***");
    }

    // User update(User user,
    //                long id,
    //                boolean isAdmin);
    @Transactional
    @WithUserDetails("Vivi")
    @Test
    public void G_update()
    {
        ArrayList<UserRoles> user = new ArrayList<>();
        User u2 = new User("Vivi", "password", "vivi@lambda.school", user);

        User updatedu2 = userService.update(u2, 5, false);

        System.out.println("*** DATA ***");
        System.out.println(updatedu2);
        System.out.println("*** DATA ***");
    }

    @Transactional
    @WithUserDetails("Vivi")
    @Test (expected = ResourceFoundException.class)
    public void GA_updateWithUserRole()
    {
        Role r2 = new Role("user");

        ArrayList<UserRoles> user = new ArrayList<>();
        User u2 = new User("Vivi", "password", "vivi@lambda.school", user);
        user.add(new UserRoles(u2, r2));

        User updatedu2 = userService.update(u2, 5, false);

        System.out.println("*** DATA ***");
        System.out.println(updatedu2);
        System.out.println("*** DATA ***");
    }

    @Transactional
    @WithUserDetails("Vivi")
    @Test (expected = ResourceNotFoundException.class)
    public void GB_updateNotCurrentUserNorAdmin()
    {
        Role r2 = new Role("user");

        ArrayList<UserRoles> user = new ArrayList<>();
        User u2 = new User("Vivi", "password", "cinnamon@school.lambda", user);

        User updatedu2 = userService.update(u2, 8, false);

        System.out.println("*** DATA ***");
        System.out.println(updatedu2);
        System.out.println("*** DATA ***");
    }

    @Test(expected = ResourceNotFoundException.class)
    public void HA_deleteUserRoleRoleNotFound()
    {
        userService.deleteUserRole(7, 50);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void HB_deleteUserRoleUserNotFound()
    {
        userService.deleteUserRole(50, 2);
    }

    // void deleteUserRole(long userid,
    //                        long roleid);
    @Test
    public void IB_deleteUserRole()
    {
        userService.deleteUserRole(13, 2);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void IC_addUserRoleRoleNotFound()
    {
        userService.addUserRole(7, 50);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void ID_addUserRoleUserNotFound()
    {
        userService.addUserRole(50, 2);
    }

    // void addUserRole(long userid,
    //                     long roleid);
    @Test
    public void IE_addUserRole()
    {
        userService.addUserRole(13, 3);
    }
}
