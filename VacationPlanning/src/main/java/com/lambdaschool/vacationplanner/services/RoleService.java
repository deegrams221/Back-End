package com.lambdaschool.vacationplanner.services;

import com.lambdaschool.vacationplanner.models.Role;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService
{
    List<Role> findAll(Pageable unpaged);

    Role findRoleById(long id);

    void delete(long id);

    Role save(Role role);

    Role findByName(String name);

    Role update(long id, Role role);
}