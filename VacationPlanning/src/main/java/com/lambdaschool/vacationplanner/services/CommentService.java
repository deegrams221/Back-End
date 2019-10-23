package com.lambdaschool.vacationplanner.services;

import com.lambdaschool.vacationplanner.models.Comments;
import com.lambdaschool.vacationplanner.models.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService
{
    List<Comments> findAll(Pageable pageable);

    Comments findCommentById(long comid);

    void deleteComment(long comid);

    Comments save(Comments comments);
}