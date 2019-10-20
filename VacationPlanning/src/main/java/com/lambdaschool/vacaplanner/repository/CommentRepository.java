package com.lambdaschool.vacaplanner.repository;

import com.lambdaschool.vacaplanner.models.Comments;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comments, Long>
{

    List<Comments> findAll(Pageable pageable);

    List<Comments> findAllByVacation(String vacation);
}
