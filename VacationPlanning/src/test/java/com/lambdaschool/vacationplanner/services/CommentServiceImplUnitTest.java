//package com.lambdaschool.vacationplanner.services;
//
//
//        import com.lambdaschool.vacationplanner.models.*;
//        import com.lambdaschool.vacationplanner.repository.*;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.boot.CommandLineRunner;
//        import org.springframework.stereotype.Component;
//        import org.springframework.transaction.annotation.Transactional;
//        import com.lambdaschool.vacationplanner.services.CommentService;
//
//        import java.util.ArrayList;
//
//@Transactional
//@Component
//public class CommentServiceImplUnitTest
//{
//    @Autowired
//    private RoleRepository rolerepos;
//
//    @Autowired
//    private UserRepository userrepos;
//
//    @Autowired
//    private TodoRepository todorepos;
//
//    @Autowired
//    private VacationRepository vacarepos;
//
//    @Autowired
//    private CommentRepository comrepos;
//
//    @Autowired
//    private CommentService comService;
//
//    public SeedData(RoleRepository rolerepos,
//                    UserRepository userrepos,
//                    TodoRepository todorepos,
//                    VacationRepository vacarepos,
//                    CommentRepository comrepos,
//                    CommentService comService)
//    {
//        this.rolerepos = rolerepos;
//        this.userrepos = userrepos;
//        this.todorepos = todorepos;
//        this.vacarepos = vacarepos;
//        this.comrepos = comrepos;
//        this.comService = comService;
//    }
//
//    @Override
//    public void run(String[] args) throws Exception
//    {
//        // roles
//        Role r1 = new Role("admin");
//        Role r2 = new Role("user");
//        Role r3 = new Role ("data");
//
//        ArrayList<UserRoles> admin = new ArrayList<>();
//        admin.add(new UserRoles(new User(), r1));
//
//        ArrayList<UserRoles> user = new ArrayList<>();
//        user.add(new UserRoles(new User(), r2));
//
//        ArrayList<UserRoles> data = new ArrayList<>();
//        data.add(new UserRoles(new User(), r3));
//
//        rolerepos.save(r1);
//        rolerepos.save(r2);
//        rolerepos.save(r3);
//
//        // users
//        User u1 = new User("Admin", "password", "admin@lambda.school", admin);
//        User u2 = new User("Vivi", "password", "vivi@lambda.school", user);
//        user = new ArrayList<>();
//        user.add(new UserRoles(new User(), r2));
//        User u3 = new User("Cloud", "password", "cloud@lambda.school",user);
//        user = new ArrayList<>();
//        user.add(new UserRoles(new User(), r2));
//        User u4 = new User("Leonhart", "password", "leonhart@lambda.school",user);
//        user = new ArrayList<>();
//        user.add(new UserRoles(new User(), r2));
//        User u5 = new User("Yuna", "password", "yuna@lambda.school",user);
//        user = new ArrayList<>();
//        user.add(new UserRoles(new User(), r2));
//        User u6 = new User("Sephiroth", "password", "sephiroth@lambda.school",user);
//
//        // vacations
//        u1.getVacations().add(new Vacations("Hawaii", "2019-10-19", u1));
//        u2.getVacations().add(new Vacations("Spain", "2019-10-19", u2));
//        u2.getVacations().add(new Vacations("New Zealand", "2019-10-19", u2));
//        u3.getVacations().add(new Vacations("Rome", "2019-10-20", u3));
//        u4.getVacations().add(new Vacations("Disney World", "2019-10-20", u4));
//        u5.getVacations().add(new Vacations("Disneyland", "2019-10-20", u5));
//
//        userrepos.save(u1);
//        userrepos.save(u2);
//        userrepos.save(u3);
//        userrepos.save(u4);
//        userrepos.save(u5);
//        userrepos.save(u6);
//
//        // comments
//        u1.getComments().add(new Comments("Lets go fishing!", u1, u1.getVacations().get(0)));
//        // u2.getComments().add(new Comments("I think we should go surfing!"));
//
//        // todos (activities)
//
//
//    }
//}
