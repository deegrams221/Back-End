package com.lambdaschool.vacationplanner.controllers;

import com.lambdaschool.vacationplanner.models.Comments;
import com.lambdaschool.vacationplanner.models.ErrorDetail;
import com.lambdaschool.vacationplanner.models.Vacations;
import com.lambdaschool.vacationplanner.services.CommentService;
import com.lambdaschool.vacationplanner.services.UserService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "/comments")
// http://localhost:2019/comments
// http://bw-vacaplanning.herokuapp.com/comments
public class CommentController
{
    // logging
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService comService;

    @Autowired
    private UserService userService;

    // Adding custom swagger documentation for find All comments with paging/sorting
    @ApiOperation(value = "List All Comments",
            response = Vacations.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Found Comments",
                    response = void.class),
            @ApiResponse(code = 404,
                    message = "Comments Not Found",
                    response = ErrorDetail.class)})

    // GET: /comments
    @GetMapping(value = "/comments",
            produces = {"application/json"})
    public ResponseEntity<?> findAll(HttpServletRequest request)
    {
        // logger
        logger.trace(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed.");

        List<Comments> allCom = comService.findAll();

        return new ResponseEntity<>(allCom, HttpStatus.OK);
    }

    // Adding custom swagger documentation for find a comment by id
    @ApiOperation(value = "Find Comment By Id",
            response = Vacations.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201,
                    message = "Found Comment",
                    response = void.class),
            @ApiResponse(code = 404,
                    message = "Comment Not Found",
                    response = ErrorDetail.class)})

    // GET: /comments/{comid}
    @GetMapping(value = "/comments/{comid}",
            produces = {"application/json"})
    public ResponseEntity<?> findCommentById(HttpServletRequest request,
                                             @PathVariable Long comid)
    {
        // logger
        logger.info(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed.");

        Comments c = comService.findCommentById(comid);

        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    // Adding custom swagger documentation for delete a comment
    @ApiOperation(value = "Delete A Comment",
            response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201,
                    message = "Cooment Deleted",
                    response = void.class),
            @ApiResponse(code = 500,
                    message = "Could Not Delete Comment",
                    response = ErrorDetail.class)})

    // DELETE /comments/{comid}
    @DeleteMapping("/comments/{comid}")
    public ResponseEntity<?> deleteComment(
            // Adding custom swagger params
            @ApiParam(value = "Comment Id",
                    required = true,
                    example = "1")
            @PathVariable long comid,
            HttpServletRequest request)
    {
        // logging
        logger.info(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed.");

        comService.deleteComment(comid);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Adding custom swagger documentation for sava a comment
    @ApiOperation(value = "Add A New Comment",
                notes = "New Comment Id will be sent to the location header",
                response = void.class)
    @ApiResponses(value = {@ApiResponse(code = 201,
                message = "Comment Added",
                response = void.class),
                @ApiResponse(code = 500,
                        message = "Could Not Add Comment",
                        response = ErrorDetail.class)})

        // POST:  /comments/newcom
        //    {
        //        "detail": "Let's go fishing!"
        //    }
        @PostMapping(value = "/newcom",
                consumes = {"application/json"},
                produces = {"application/json"})
        public ResponseEntity<?> createComment(@Valid
                                                   @RequestBody Comments newcom) throws URISyntaxException
        {
            newcom =  comService.save(newcom);
            // set the location header for the newly created resource
            HttpHeaders responseHeaders = new HttpHeaders();
            URI newComURI = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{comid}")
                    .buildAndExpand(newcom.getComid())
                    .toUri();
            responseHeaders.setLocation(newComURI);
            return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
        }
    }

