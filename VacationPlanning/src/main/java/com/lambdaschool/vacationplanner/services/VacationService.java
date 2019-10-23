package com.lambdaschool.vacationplanner.services;

import com.lambdaschool.vacationplanner.models.Vacations;

import java.util.ArrayList;

public interface VacationService
{
    ArrayList<Vacations> findAllVacas();

    Vacations findVacationById(long vacaid);

    Vacations findByPlace(String place);

    void delete(long vacaid);

    Vacations save(Vacations vacations);

    Vacations update(Vacations updateVacation, long vacaid);
}
