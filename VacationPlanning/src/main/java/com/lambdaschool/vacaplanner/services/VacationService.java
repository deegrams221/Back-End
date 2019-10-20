package com.lambdaschool.vacaplanner.services;

import com.lambdaschool.vacaplanner.models.Vacations;

public interface VacationService
{
    Vacations findVacationById(long vacaid);

    Vacations findByPlace(String place);

    void delete(long vacaid);

    Vacations save(Vacations vacations);

    Vacations update(Vacations vacations,
                     long vacaid,
                     boolean isUser);
}
