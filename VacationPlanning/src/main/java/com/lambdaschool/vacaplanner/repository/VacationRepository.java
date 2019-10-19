package com.lambdaschool.vacaplanner.repository;

import com.lambdaschool.vacaplanner.models.Vacations;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface VacationRepository extends PagingAndSortingRepository<Vacations, Long>
{
    Vacations findVacationsByPlace(String place);

    List<Vacations> findVacationsByPlaceContainingIgnoreCase(String place,
                                                             Pageable pageable);
}
