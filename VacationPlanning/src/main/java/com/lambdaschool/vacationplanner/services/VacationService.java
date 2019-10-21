package com.lambdaschool.vacationplanner.services;

import com.lambdaschool.vacationplanner.models.Vacations;

public interface VacationService
{
    Vacations findVacationById(long vacaid);

    Vacations findByPlace(String place);

    void delete(long vacaid);

    Vacations save(Vacations vacations);

    Vacations update(Vacations updateVacation, long vacaid);
}
