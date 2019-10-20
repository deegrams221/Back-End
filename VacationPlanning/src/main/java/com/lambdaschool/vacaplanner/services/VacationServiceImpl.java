package com.lambdaschool.vacaplanner.services;

import com.lambdaschool.vacaplanner.exceptions.ResourceNotFoundException;
import com.lambdaschool.vacaplanner.models.Vacations;
import com.lambdaschool.vacaplanner.repository.VacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

public class VacationServiceImpl implements VacationService
{
    @Autowired
    private VacationRepository vacarepos;

    @Override
    public Vacations findVacationById(long vacaid) throws ResourceNotFoundException
    {
        return vacarepos.findById(vacaid)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Vacation id " + vacaid + " not found!"));
    }

    @Override
    public Vacations findByPlace(String place)
    {
        return vacarepos.findVacationsByPlace(place);
    }

    @Override
    public void delete(long vacaid)
    {
        vacarepos.findById(vacaid)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Vacation id " + vacaid + " not found!"));
        vacarepos.deleteById(vacaid);
    }

    @Override
    public Vacations save(Vacations vacations)
    {
        Vacations newVacation = new Vacations();
        newVacation.setPlace(vacations.getPlace());
        newVacation.setDate(vacations.getDate());

        return vacarepos.save(newVacation);
    }
}
