package com.lambdaschool.vacaplanner.services;


import com.lambdaschool.vacaplanner.models.Comments;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService
{
    List<Comments> findAll(Pageable pageable);

    Comments findCommentById(long comid);

    List<Comments> findByVacation(String vacation,
                                  boolean isUser);

    void deleteComment(long comid);

    Comments save(Comments comments);

}
