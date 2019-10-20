package com.lambdaschool.vacaplanner.repository;

import com.lambdaschool.vacaplanner.models.Useremail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UseremailRepository extends CrudRepository<Useremail, Long>
{
    List<Useremail> findAllByUser_UserName(String userName);
}
