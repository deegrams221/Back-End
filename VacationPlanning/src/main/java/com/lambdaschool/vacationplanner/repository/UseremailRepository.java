package com.lambdaschool.vacationplanner.repository;

import com.lambdaschool.vacationplanner.models.Useremail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UseremailRepository extends CrudRepository<Useremail, Long>
{
    List<Useremail> findAllByUser_Username(String username);
}
