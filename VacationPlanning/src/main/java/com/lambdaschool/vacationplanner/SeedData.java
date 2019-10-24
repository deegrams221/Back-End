package com.lambdaschool.vacationplanner;

import com.lambdaschool.vacationplanner.models.*;
import com.lambdaschool.vacationplanner.repository.RoleRepository;
import com.lambdaschool.vacationplanner.repository.UserRepository;
import com.lambdaschool.vacationplanner.repository.VacationRepository;
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

        // vacations
        Vacations v1 = new Vacations("Hawaii", u1);
//        u2.getVacations().add(new Vacations("Hawaii"));
//        u2.getVacations().add(new Vacations("Spain"));
//        u2.getVacations().add(new Vacations("New Zealand"));
//        u3.getVacations().add(new Vacations("Rome"));
//        u4.getVacations().add(new Vacations("Disney World"));
//        u5.getVacations().add(new Vacations("Disneyland"));

        userrepos.save(u1);
        userrepos.save(u2);
        userrepos.save(u3);
        userrepos.save(u4);
        userrepos.save(u5);
        userrepos.save(u6);

        vacarepos.save(v1);

        // comments
        u3.getComments().add(new Comments("Lets go fishing!"));
        u2.getComments().add(new Comments("I think we should go surfing!"));
        u3.getComments().add(new Comments("Maybe we can go fishing and go surfing?"));
        u2.getComments().add(new Comments("That sounds fun! Great idea!"));

        // todos (activities)
//        u3.getTodos().add(new Todos("Fishing", "We'll need to get some gear!", u3.getVacations().get(0), u3));
//        u2.getTodos().add(new Todos("Surfing", "We'll need just need to rent some boards.", u2.getVacations().get(0), u2));
    }
}
