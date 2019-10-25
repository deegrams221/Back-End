package com.lambdaschool.vacationplanner.repository;

import com.lambdaschool.vacationplanner.models.Comments;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comments, Long>
{
    List<Comments> findAll();
}
