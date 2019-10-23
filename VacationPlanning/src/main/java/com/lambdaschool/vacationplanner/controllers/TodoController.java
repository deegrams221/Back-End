package com.lambdaschool.vacationplanner.controllers;

import com.lambdaschool.vacationplanner.logging.Loggable;
import com.lambdaschool.vacationplanner.models.ErrorDetail;
import com.lambdaschool.vacationplanner.models.Todos;
import com.lambdaschool.vacationplanner.models.User;
import com.lambdaschool.vacationplanner.services.TodoService;
import com.lambdaschool.vacationplanner.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

@Loggable
@RestController
@RequestMapping("/todos")
// http://localhost:2019/todos
// http://bw-vacaplanning.herokuapp.com/todos
public class TodoController
{
    // logger
    private static final Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private TodoService todoService;

    @Autowired
    private UserService userService;

    // Adding custom swagger documentation for find Todos By Id
    @ApiOperation(value = "find Todo By Id",
            response = Todos.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201,
                    message = "Found Todo",
                    response = void.class),
            @ApiResponse(code = 500,
                    message = "Todo Not Found",
                    response = ErrorDetail.class)})

    // GET: /todos/{todoid}
    @GetMapping(value = "/todos/{todoid}",
            produces = {"application/json"})
    public ResponseEntity<?> findTodoById(HttpServletRequest request,
                                          @PathVariable Long todoid)
    {
        // logger
        logger.info(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed.");

        Todos t = todoService.findTodoById(todoid);

        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    // Adding custom swagger documentation for find Todos By Title
    @ApiOperation(value = "find Todo By Title",
            response = Todos.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201,
                    message = "Found Todo",
                    response = void.class),
            @ApiResponse(code = 500,
                    message = "Todo Not Found",
                    response = ErrorDetail.class)})

    // GET: /todos/{title}
    @GetMapping(value = "/todos/{title}",
            produces = {"application/json"})
    public ResponseEntity<?> findByTitle(HttpServletRequest request,
                                         @PathVariable String tilte)
    {
        // logger
        logger.info(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed.");

        Todos tt = todoService.findByTitle(tilte);

        return new ResponseEntity<>(tt, HttpStatus.OK);
    }

    // Adding custom swagger documentation for add new todos
    @ApiOperation(value = "Add a new todo",
            notes = "New todo Id will be sent to the location header",
            response = void.class)
    @ApiResponses(value = {@ApiResponse(code = 201,
            message = "Todo Added",
            response = void.class),
            @ApiResponse(code = 500,
                    message = "Could Not Add Todo",
                    response = ErrorDetail.class)})

    // POST:  /todos/{vacaid}
    @PostMapping(value = "/todos/{vacaid}",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addNewTodo(HttpServletRequest request,
                                        @Valid
                                        @RequestBody Todos newtodo,
                                        Authentication authentication,
                                        @PathVariable long vacaid) throws URISyntaxException
    {
        // logger
        logger.trace(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed.");

        User user = userService.findByName(authentication.getName());
        newtodo = todoService.save(newtodo);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{todoid}")
                .buildAndExpand(newtodo.getTodoid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null,
                responseHeaders,
                HttpStatus.CREATED);
    }
}
