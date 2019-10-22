package com.lambdaschool.vacationplanner.services;

import com.lambdaschool.vacationplanner.VacationplannerApplication;
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

import javax.annotation.security.DeclareRoles;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VacationplannerApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class RoleServiceImplUnitTest
{
    @Autowired
    private RoleService roleService;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception
    {}

    // Tests
    // List<Role> findAll(Pageable unpaged);
    @Test
    public void A_findAll()
    {
        assertEquals(3, roleService.findAll(Pageable.unpaged()).size());
    }

    // Role findRoleById(long id);
    @Test
    public void B_findUserById()
    {
        assertEquals("ADMIN", roleService.findRoleById(1).getName());
    }

    // void delete(long id);
    @Test
    public void D_delete()
    {
        roleService.delete(3);
        assertEquals(2, roleService.findAll(Pageable.unpaged()).size());
    }

    // Role save(Role role);
    @Test
    public void F_save()
    {
        ArrayList<UserRoles> roles = new ArrayList<>();
        Role r2 = new Role("UserTwo");

        Role saveR2 = roleService.save(r2);

        System.out.println("*** DATA ***");
        System.out.println(saveR2);
        System.out.println("*** DATA ***");
    }

    //    Role findByName(String name);
    @Test
    public void E_findByName()
    {
        assertEquals("ADMIN", roleService.findByName("admin").getName());
    }


    //    Role update(long id, Role role);
    @Test
    public void G_update()
    {
        ArrayList<UserRoles> roles = new ArrayList<>();
        Role r2 = new Role("DataBlue");

        Role updatedR2 = roleService.update(2, r2);

        System.out.println("*** DATA ***");
        System.out.println(updatedR2);
        System.out.println("*** DATA ***");
    }
}
