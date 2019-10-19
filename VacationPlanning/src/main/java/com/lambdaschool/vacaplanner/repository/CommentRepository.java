package com.lambdaschool.vacaplanner.repository;

import com.lambdaschool.vacaplanner.models.Comments;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comments, Long>
{

}
