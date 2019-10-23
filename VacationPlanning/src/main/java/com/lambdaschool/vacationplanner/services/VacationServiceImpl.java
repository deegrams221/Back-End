package com.lambdaschool.vacationplanner.services;

import com.lambdaschool.vacationplanner.exceptions.ResourceNotFoundException;
import com.lambdaschool.vacationplanner.models.User;
import com.lambdaschool.vacationplanner.models.Vacations;
import com.lambdaschool.vacationplanner.repository.VacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "vacaService")
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
    public Vacations save(Vacations vacations, User user)
    {
        Vacations newVacation = new Vacations();
        newVacation.setPlace(vacations.getPlace());

        return vacarepos.save(newVacation);
    }

    @Override
    public Vacations update(Vacations vacations, long vacaid) {
        Vacations currentVacation = vacarepos.findById(vacaid)
                .orElseThrow(() -> new ResourceNotFoundException(Long.toString(vacaid)));

        if (vacations.getPlace() != null)
        {
            currentVacation.setPlace(vacations.getPlace());
        }

        return vacarepos.save(currentVacation);
    }
}
