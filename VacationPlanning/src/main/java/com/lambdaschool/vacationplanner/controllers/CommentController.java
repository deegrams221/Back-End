package com.lambdaschool.vacationplanner.controllers;

import com.lambdaschool.vacationplanner.models.Comments;
import com.lambdaschool.vacationplanner.models.ErrorDetail;
import com.lambdaschool.vacationplanner.models.User;
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
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "/comments")
// http://localhost:2019/comment
// http://bw-vacaplanning.herokuapp.com/comment
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
            @ApiResponse(code = 201,
                    message = "Found Comments",
                    response = void.class),
            @ApiResponse(code = 404,
                    message = "Comments Not Found",
                    response = ErrorDetail.class)})
    @ApiImplicitParams({@ApiImplicitParam(name = "page",
            dataType = "integer",
            paramType = "query",
            value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size",
                    dataType = "integer",
                    paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort",
                    allowMultiple = true,
                    dataType = "string",
                    paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). "
                            + "Default sort order is ascending. "
                            + "Multiple sort criteria are supported.")})

    // paging and sorting
    // GET:  /comments/?page=1&size=10
    // GET: /comments
    @GetMapping(value = "/comments",
            produces = {"application/json"})
    public ResponseEntity<?> findAll(HttpServletRequest request,
                                     @PageableDefault(page = 0, size = 5)
                                             Pageable pageable)
    {
        // logger
        logger.trace(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed.");

        List<Comments> allCom = comService.findAll(pageable);

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
                                             @PathVariable Long comid) {
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
            HttpServletRequest request) {
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

    // POST:  /comments/{vacaid}
    @PostMapping(value = "/comments/{vacaid}",
                consumes = {"application/json"},
                produces = {"application/json"})
    public ResponseEntity<?> save(
                        HttpServletRequest request,
                        @Valid
                        @RequestBody Comments newCom,
                        Authentication authentication,
                        @PathVariable long vacaid) throws URISyntaxException
    {
        // logger
        logger.trace(request.getMethod().toUpperCase()
                    + " " + request.getRequestURI() + " accessed.");

        User user = userService.findByName(authentication.getName());
        newCom = comService.save(newCom, user, vacaid);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{comid}")
                    .buildAndExpand(newCom.getComid())
                    .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null,
                                            responseHeaders,
                                            HttpStatus.CREATED);
    }
}
