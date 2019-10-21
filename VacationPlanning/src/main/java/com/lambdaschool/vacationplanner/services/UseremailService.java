package com.lambdaschool.vacationplanner.services;

import com.lambdaschool.vacationplanner.models.Useremail;

import java.util.List;

public interface UseremailService
{
    List<Useremail> findAll();

    Useremail findUseremailById(long id);

    List<Useremail> findByUsername(String username,
                                   boolean isAdmin);

    void delete(long id,
                boolean isAdmin);

    Useremail update(long useremailid,
                     String emailaddress,
                     boolean isAdmin);
}
