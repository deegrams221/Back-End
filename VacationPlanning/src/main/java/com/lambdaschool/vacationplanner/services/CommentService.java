package com.lambdaschool.vacationplanner.services;

import com.lambdaschool.vacationplanner.models.Comments;

import java.util.List;

public interface CommentService
{
    List<Comments> findAll();

    Comments findCommentById(long comid);

    void deleteComment(long comid);

    Comments save(Comments comments);
}