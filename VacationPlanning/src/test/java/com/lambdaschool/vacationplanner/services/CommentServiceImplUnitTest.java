package com.lambdaschool.vacationplanner.services;

import com.lambdaschool.vacationplanner.VacationplannerApplication;
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

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception
    {}

    // Tests
    // Comments findCommentById(long comid);
    @Test
    public void B_findCommentById()
    {
        assertEquals("That sounds fun! Great idea!", comService.findCommentById(19).getDetail());
    }
}
