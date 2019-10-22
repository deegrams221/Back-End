package com.lambdaschool.vacationplanner.services;

import com.lambdaschool.vacationplanner.VacationplannerApplication;
import com.lambdaschool.vacationplanner.models.Comments;
import com.lambdaschool.vacationplanner.repository.CommentRepository;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VacationplannerApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class CommentServiceImplUnitTest
{
    @Autowired
    private CommentService comService;

    @Autowired
    private VacationService vacaService;

    @Autowired
    private CommentRepository comrepos;

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
    // List<Comments> findAll(Pageable pageable);
    @Test
    public void A_findAll()
    {
        assertEquals(13, comService.findAll(Pageable.unpaged()).size());
    }

    // Comments findCommentById(long comid);
    @Test
    public void B_findCommentById()
    {
        assertEquals("Lets go fishing!", comService.findCommentById(19).getDetail());
    }

    // void deleteComment(long comid);
    @Test
    public void D_deleteComment()
    {
        comService.deleteComment(19);
        assertEquals(3, comService.findAll(Pageable.unpaged()).size());
    }

    // Comments save(Comments comments, User user, long vacaid);
    @Test
    public void F_save()
    {
        // ArrayList<Comments> comments = new ArrayList<>();
        Comments c1 = new Comments("Fishing is fun!", userService.findUserById(15), vacaService.findVacationById(6));

        Comments saveC1 = comService.save(c1, userService.findUserById(15), 6);

        System.out.println("*** DATA ***");
        System.out.println(saveC1);
        System.out.println("*** DATA ***");
    }
}
