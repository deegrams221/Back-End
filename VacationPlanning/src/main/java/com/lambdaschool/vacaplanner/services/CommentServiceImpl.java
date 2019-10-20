package com.lambdaschool.vacaplanner.services;

import com.lambdaschool.vacaplanner.exceptions.ResourceNotFoundException;
import com.lambdaschool.vacaplanner.logging.Loggable;
import com.lambdaschool.vacaplanner.models.Comments;
import com.lambdaschool.vacaplanner.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Override
    public List<Comments> findByVacation(String vacation,
                                         boolean isUser)
    {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        if (vacation.equalsIgnoreCase(authentication.getName().toLowerCase()) || isUser)
        {
            return comrepos.findAllByVacation(vacation.toLowerCase());
        } else
        {
            throw new ResourceNotFoundException("Not authorized to make change");
        }
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
