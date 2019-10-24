package com.lambdaschool.vacationplanner;

import com.lambdaschool.vacationplanner.models.*;
import com.lambdaschool.vacationplanner.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    private RoleRepository rolerepos;

    @Autowired
    private UserRepository userrepos;

    @Autowired
    private VacationRepository vacarepos;

    @Autowired
    private CommentRepository comrepos;

    @Autowired
    private TodoRepository todorepos;

    public SeedData(RoleRepository rolerepos,
                    UserRepository userrepos)
    {
        this.rolerepos = rolerepos;
        this.userrepos = userrepos;
    }

    @Override
    public void run(String[] args) throws Exception
    {
        // roles
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role ("data");

        ArrayList<UserRoles> admin = new ArrayList<>();
        admin.add(new UserRoles(new User(), r1));

        ArrayList<UserRoles> user = new ArrayList<>();
        user.add(new UserRoles(new User(), r2));

        ArrayList<UserRoles> data = new ArrayList<>();
        data.add(new UserRoles(new User(), r3));

        rolerepos.save(r1);
        rolerepos.save(r2);
        rolerepos.save(r3);

        // users
        User u1 = new User("admin", "password", "admin@lambda.school", admin);
        User u2 = new User("Vivi", "password", "vivi@lambda.school", user);
        user = new ArrayList<>();
        user.add(new UserRoles(new User(), r2));
        User u3 = new User("Cloud", "password", "cloud@lambda.school",user);
        user = new ArrayList<>();
        user.add(new UserRoles(new User(), r2));
        User u4 = new User("Leonhart", "password", "leonhart@lambda.school",user);
        user = new ArrayList<>();
        user.add(new UserRoles(new User(), r2));
        User u5 = new User("Yuna", "password", "yuna@lambda.school",user);
        user = new ArrayList<>();
        user.add(new UserRoles(new User(), r2));
        User u6 = new User("Sephiroth", "password", "sephiroth@lambda.school",user);

        userrepos.save(u1);
        userrepos.save(u2);
        userrepos.save(u3);
        userrepos.save(u4);
        userrepos.save(u5);
        userrepos.save(u6);

        // vacations
        Vacations v1 = new Vacations("Hawaii", u1);
        Vacations v2 = new Vacations("Spain", u2);
        Vacations v3 = new Vacations("New Zealand", u2);
        Vacations v4 = new Vacations("Rome", u3);
        Vacations v5 = new Vacations("Disney World", u4);
        Vacations v6 = new Vacations("Disneyland", u5);

        vacarepos.save(v1);
        vacarepos.save(v2);
        vacarepos.save(v3);
        vacarepos.save(v4);
        vacarepos.save(v5);
        vacarepos.save(v6);

        // comments
        Comments c1 = new Comments("Lets go fishing!", u2);
        Comments c2 = new Comments("I think we should go surfing!", u3);
        Comments c3 = new Comments("Maybe we can go fishing and go surfing?", u2);
        Comments c4 = new Comments("That sounds fun! Great idea!", u3);

        comrepos.save(c1);
        comrepos.save(c2);
        comrepos.save(c3);
        comrepos.save(c4);


        // todos (activities)
        Todos t1 = new Todos("Fishing", "We'll need to get some gear!", u3);
        Todos t2 = new Todos("Surfing", "We'll need just need to rent some boards.", u2);

        todorepos.save(t1);
        todorepos.save(t2);
    }
}
