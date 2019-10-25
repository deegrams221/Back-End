package com.lambdaschool.vacationplanner.repository;

import com.lambdaschool.vacationplanner.models.Vacations;
import com.lambdaschool.vacationplanner.view.JustTheCount;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface VacationRepository extends PagingAndSortingRepository<Vacations, Long>
{
    Vacations findVacationsByPlace(String place);

    List<Vacations> findVacationsByPlaceContainingIgnoreCase(String place);
}