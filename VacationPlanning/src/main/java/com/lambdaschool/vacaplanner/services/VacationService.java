package com.lambdaschool.vacaplanner.services;

import com.lambdaschool.vacaplanner.models.Vacations;

public interface VacationService
{
    Vacations findVacationById(long id);

    Vacations findByPlace(String place);

    void delete(long id);

    Vacations save(Vacations vacations);

    Vacations update(Vacations vacations, long id);
}
