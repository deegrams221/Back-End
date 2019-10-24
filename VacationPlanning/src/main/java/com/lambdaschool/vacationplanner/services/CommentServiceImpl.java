package com.lambdaschool.vacationplanner.services;

import com.lambdaschool.vacationplanner.exceptions.ResourceNotFoundException;
import com.lambdaschool.vacationplanner.logging.Loggable;
import com.lambdaschool.vacationplanner.models.Comments;
import com.lambdaschool.vacationplanner.models.User;
import com.lambdaschool.vacationplanner.models.Vacations;
import com.lambdaschool.vacationplanner.repository.CommentRepository;
import com.lambdaschool.vacationplanner.repository.UserRepository;
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

    @Autowired
    private VacationService vacationService;

    @Autowired
    private UserRepository userrepos;

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

//    @Transactional
//    @Override
//    public Comments save(Comments comments, User user, long vacaid)
//    {
//        Comments newComment = new Comments();
//        Vacations vacations = vacationService.findVacationById(vacaid);
//        newComment.setDetail(comments.getDetail());
//        newComment.setUser(comments.getUser());
////        newComment.setVacations(vacations);
//
//        return comrepos.save(newComment);
//    }

    @Transactional
    @Override
    public Comments save(Comments comments)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userrepos.findByUsername(authentication.getName());

        Comments newCom = new Comments();
        newCom.setDetail(comments.getDetail());
        newCom.setUser(currentUser);

        List<Comments> userCom = currentUser.getComments();
        userCom.add(newCom);
        currentUser.setComments(userCom);

        return comrepos.save(newCom);
    }

}
