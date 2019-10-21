package com.lambdaschool.vacationplanner.services;

import com.lambdaschool.vacationplanner.exceptions.ResourceNotFoundException;
import com.lambdaschool.vacationplanner.logging.Loggable;
import com.lambdaschool.vacationplanner.models.Comments;
import com.lambdaschool.vacationplanner.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Loggable
@Service(value = "comService")
public class CommentServiceImpl implements CommentService
{
    @Autowired
    private CommentRepository comrepos;

    @Override
    public List<Comments> findAll(Pageable pageable)
    {
        List<Comments> list = new ArrayList<>();
        comrepos.findAll(pageable)
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Comments findCommentById(long comid)
    {
        return comrepos.findById(comid)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Comment id " + comid + " not found!"));
    }

    @Transactional
    @Override
    public void deleteComment(long comid)
    {
        comrepos.findById(comid)
                .orElseThrow(() -> new ResourceNotFoundException("Comment id " + comid + " not found!"));
        comrepos.deleteById(comid);
    }

    @Transactional
    @Override
    public Comments save(Comments comments)
    {
        Comments newComment = new Comments();
        newComment.setDetail(comments.getDetail());

        return comrepos.save(newComment);
    }

}