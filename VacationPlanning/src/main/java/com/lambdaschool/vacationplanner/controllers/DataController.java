package com.lambdaschool.vacationplanner.controllers;

import com.lambdaschool.vacationplanner.models.ErrorDetail;
import com.lambdaschool.vacationplanner.models.Todos;
import com.lambdaschool.vacationplanner.models.Vacations;
import com.lambdaschool.vacationplanner.services.TodoService;
import com.lambdaschool.vacationplanner.services.VacationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/data")
// http://localhost:2019/data
// http://bw-vacaplanning.herokuapp.com/data
 public class DataController
{
    // logging
    private static final Logger logger = LoggerFactory.getLogger(DataController.class);

    @Autowired
    private VacationService vacaService;

    @Autowired
    private TodoService todoService;

    // Adding custom swagger documentation for update vacation
    @ApiOperation(value = "Updates vacation details", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201,
                    message = "Vacation Updated",
                    response = void.class),
            @ApiResponse(code = 500,
                    message = "Vacation Not Updated",
                    response = ErrorDetail.class)})

    // PUT:  /vacations/{vacaid}
    @PutMapping(value = "/vacations/{vacaid}")
    public ResponseEntity<?> update(
            // Adding custom swagger params
            @ApiParam(value = "Vacation Id",
                    required = true,
                    example = "1")
            @RequestBody Vacations updateVacation,
            @PathVariable long vacaid, HttpServletRequest request)
    {
        // logger
        logger.info(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed.");

        vacaService.update(updateVacation, vacaid);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Adding custom swagger documentation for update todos
    @ApiOperation(value = "Updates todo details", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201,
                    message = "Todo Updated",
                    response = void.class),
            @ApiResponse(code = 500,
                    message = "Todo Not Updated",
                    response = ErrorDetail.class)})

    // PUT:  /todos/{todoid}
    @PutMapping(value = "/todos/{todoid}")
    public ResponseEntity<?> update(
            // Adding custom swagger params
            @ApiParam(value = "Todo Id",
                    required = true,
                    example = "1")
            @RequestBody Todos updateTodo,
            @PathVariable long todid, HttpServletRequest request)
    {
        // logger
        logger.info(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed.");

        todoService.update(updateTodo, todid);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Adding custom swagger documentation for deleting a todos
    @ApiOperation(value = "Delete a todo",
            response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201,
                    message = "Todo Deleted",
                    response = void.class),
            @ApiResponse(code = 500,
                    message = "Could Not Delete Todo",
                    response = ErrorDetail.class)})

    // DELETE /todos/{todoid}
    @DeleteMapping("/todos/{todoid}")
    public ResponseEntity<?> delete(
            // Adding custom swagger params
            @ApiParam(value = "Todo Id",
                    required = true,
                    example = "1")
            @PathVariable long todoid,
            HttpServletRequest request)
    {
        // logging
        logger.info(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed.");

        todoService.delete(todoid);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Adding custom swagger documentation for assigning a todos
    @ApiOperation(value = "Assigns a todo already in the system",
            notes = "Todo Id will be sent to the location header",
            response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201,
                    message = "Todo assigned",
                    response = void.class),
            @ApiResponse(code = 500,
                    message = "Could Not Assign Todo",
                    response = ErrorDetail.class)})

    //  POST /todos/{todoid}/vacations/{vacaid}
    @PostMapping(value = "/todos/{todoid}/vacations/{vacaid}",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> assignTodoToVacation(
            @ApiParam(value = "Todo Id",
                    required = true,
                    example = "1")
            @PathVariable long todoid,
            @ApiParam(value = "Vacation Id",
                    required = true,
                    example = "1")
            @PathVariable long vacaid,
            HttpServletRequest request) throws URISyntaxException
    {
        // logging
        logger.info(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed.");

        todoService.assignTodoToVacation(todoid, vacaid);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
