package com.lambdaschool.vacationplanner.services;

import com.lambdaschool.vacationplanner.VacationplannerApplication;
import com.lambdaschool.vacationplanner.models.Vacations;
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

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VacationplannerApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class VacationServiceImplUnitTest
{
    @Autowired
    private VacationService vacaService;

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
    // ArrayList<Vacations> findAllVacas();
    @Test
    public void A_findAll()
    {
        assertEquals(6, vacaService.findAllVacas().size());
    }

    // Vacations findVacationById(long vacaid);
    @Test
    public void B_findVacationById()
    {
        assertEquals("Spain", vacaService.findVacationById(11).getPlace());
    }

    // Vacations findByPlace(String place);
    @Test
    public void C_findByPlace()
    {
        assertEquals("Spain", vacaService.findByPlace("Spain").getPlace());
    }

    // void delete(long vacaid);
    @Test
    public void D_delete()
    {
        vacaService.delete(11);
        assertEquals(5, vacaService.findAllVacas().size());
    }

    // Vacations save(Vacations vacations);
    @Test
    public void F_save()
    {
        Vacations va1 = new Vacations("Hollywood", userService.findUserById(6));
        va1.setVacaid(92);
        Vacations addVacation = vacaService.save(va1);

        assertNotNull(addVacation);

        Vacations foundVacation = vacaService.findVacationById(addVacation.getVacaid());
        assertEquals(addVacation.getPlace(), foundVacation.getPlace());
    }

    // Vacations update(Vacations updateVacation, long vacaid);
    @Transactional
    @Test
    public void G_update()
    {
        Vacations v1 = new Vacations("Honolulu", userService.findUserById(6));
        v1.setVacaid(10);
        Vacations vacationsR1 = vacaService.update(v1, 10);

        assertEquals("Honolulu", vacationsR1.getPlace());
    }

}
