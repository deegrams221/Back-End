package com.lambdaschool.vacaplanner.services;


import com.lambdaschool.vacaplanner.models.Comments;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService
{
    Comments findCommentById(long comid);

    List<Comments> findAllByVacaId(long vacaid,
                                   Pageable pageable);

    void deleteComment(long comid);

    Comments save(Comments comments);

    Comments update(Comments comments,
                    long comid,
                    boolean isUser);
}
