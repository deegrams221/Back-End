package com.lambdaschool.vacaplanner;

import com.lambdaschool.vacaplanner.models.Role;
import com.lambdaschool.vacaplanner.models.User;
import com.lambdaschool.vacaplanner.models.UserRoles;
import com.lambdaschool.vacaplanner.services.RoleService;
import com.lambdaschool.vacaplanner.services.UserService;
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
    RoleService roleService;

    @Autowired
    UserService userService;

    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        roleService.save(r1);
        roleService.save(r2);
        roleService.save(r3);

        // admin = user 1
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));
        admins.add(new UserRoles(new User(), r3));
        User u1 = new User("admin",
                "password",
                "admin@lambdaschool.local",
                admins);
        userService.save(u1);

        // data, user
        // user 2
        ArrayList<UserRoles> datas = new ArrayList<>();
        datas.add(new UserRoles(new User(),
                r3));
        datas.add(new UserRoles(new User(),
                r2));
        User u2 = new User("vivi",
                "password",
                "vivi@lambdaschool.local",
                datas);
        userService.save(u2);

        // user 3
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(),
                r2));
        User u3 = new User("cloud",
                "password",
                "cloud@lambdaschool.local",
                users);
        userService.save(u3);

        // user 4
        users = new ArrayList<>();
        users.add(new UserRoles(new User(),
                r2));
        User u4 = new User("leonhart",
                "password",
                "leonhart@school.lambda",
                users);
        userService.save(u4);

        // user 5
        users = new ArrayList<>();
        users.add(new UserRoles(new User(),
                r2));
        User u5 = new User("yuna",
                "password",
                "yuna@school.lambda",
                users);
        userService.save(u5);

        // user 6
        users = new ArrayList<>();
        users.add(new UserRoles(new User(),
                r2));
        User u6 = new User("sephiroth",
                "password",
                "sephiroth@school.lambda",
                users);
        userService.save(u6);
    }
}