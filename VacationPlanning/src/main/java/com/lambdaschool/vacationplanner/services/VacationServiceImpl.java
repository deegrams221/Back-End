package com.lambdaschool.vacationplanner.services;

import com.lambdaschool.vacationplanner.exceptions.ResourceNotFoundException;
import com.lambdaschool.vacationplanner.models.User;
import com.lambdaschool.vacationplanner.models.Vacations;
import com.lambdaschool.vacationplanner.repository.UserRepository;
import com.lambdaschool.vacationplanner.repository.VacationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "vacaService")
public class VacationServiceImpl implements VacationService
{
    // logger
    private static final Logger logger = LoggerFactory.getLogger(VacationServiceImpl.class);

    @Autowired
    private VacationRepository vacarepos;

    @Autowired
    private UserRepository userrepos;

    @Override
    public ArrayList<Vacations> findAllVacas()
    {
        ArrayList<Vacations> list = new ArrayList<>();
        vacarepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Vacations findVacationById(long vacaid) throws ResourceNotFoundException
    {
        return vacarepos.findById(vacaid)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Vacation id " + vacaid + " not found!"));
    }

    @Override
    public Vacations findByPlace(String place) throws EntityNotFoundException
    {
        return vacarepos.findVacationsByPlace(place);
    }

    @Transactional
    @Override
    public void delete(long vacaid) throws EntityNotFoundException
    {
        vacarepos.findById(vacaid)
            .orElseThrow(() -> new ResourceNotFoundException(
                 "Vacation id " + vacaid + " not found!"));

        logger.info("Vacation Deleted");
        vacarepos.deleteById(vacaid);
    }

    @Transactional
    @Override
    public Vacations save(Vacations vacations)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userrepos.findByUsername(authentication.getName());

        Vacations newVaca = new Vacations();
        newVaca.setPlace(vacations.getPlace());

        List<Vacations> userVaca = currentUser.getVacations();
        userVaca.add(newVaca);
        currentUser.setVacations(userVaca);

        return vacarepos.save(newVaca);
    }

    @Override
    public Vacations update(Vacations vacations, long vacaid)
    {
        Vacations currentVacation = vacarepos.findById(vacaid)
                .orElseThrow(() -> new ResourceNotFoundException(
                        Long.toString(vacaid)));

        if (vacations.getPlace() != null)
        {
            currentVacation.setPlace(vacations.getPlace());
        }

        logger.info("Updating a Vacation");
        return vacarepos.save(currentVacation);
    }
}
