package com.lambdaschool.vacationplanner.controllers;

import com.lambdaschool.vacationplanner.logging.Loggable;
import com.lambdaschool.vacationplanner.models.ErrorDetail;
import com.lambdaschool.vacationplanner.models.User;
import com.lambdaschool.vacationplanner.models.Vacations;
import com.lambdaschool.vacationplanner.services.VacationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
import com.lambdaschool.vacationplanner.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@Loggable
@RestController
@RequestMapping("/vacations")
// http://localhost:2019/vacations
// http://bw-vacaplanning.herokuapp.com/vacations
public class VacationController
{
    // logger
    private static final Logger logger = LoggerFactory.getLogger(VacationController.class);

    @Autowired
    private VacationService vacaService;

    @Autowired
    private UserService userService;

    // Adding custom swagger documentation for find Vacation By Id
    @ApiOperation(value = "find Vacation By Id",
            response = Vacations.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201,
                    message = "Found Vacation",
                    response = void.class),
            @ApiResponse(code = 500,
                    message = "Vacation Not Found",
                    response = ErrorDetail.class)})

    // GET: /vacations/{vacaid}
    @GetMapping(value = "/vacations/{vacaid}",
            produces = {"application/json"})
    public ResponseEntity<?> findVacationById(HttpServletRequest request,
                                              @PathVariable Long vacaid)
    {
        // logger
        logger.info(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed.");

        Vacations v = vacaService.findVacationById(vacaid);

        return new ResponseEntity<>(v, HttpStatus.OK);
    }

    // Adding custom swagger documentation for find Vacation By place
    @ApiOperation(value = "find Vacation By place",
            response = Vacations.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201,
                    message = "Found Vacation",
                    response = void.class),
            @ApiResponse(code = 500,
                    message = "Vacation Not Found",
                    response = ErrorDetail.class)})

    // GET: /vacations/{place}
    @GetMapping(value = "/vacations/{place}",
            produces = {"application/json"})
    public ResponseEntity<?> findByPlace(HttpServletRequest request,
                                         @PathVariable String place)
    {
        // logger
        logger.info(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed.");

        Vacations vp = vacaService.findByPlace(place);

        return new ResponseEntity<>(vp, HttpStatus.OK);
    }

    // Adding custom swagger documentation for deleting a vacation
    @ApiOperation(value = "Delete a vacation",
            response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201,
                    message = "Vacation Deleted",
                    response = void.class),
            @ApiResponse(code = 500,
                    message = "Could Not Delete Vacation",
                    response = ErrorDetail.class)})

    // DELETE /vacations/{vacaid}
    @DeleteMapping("/vacations/{vacaid}")
    public ResponseEntity<?> delete(
            // Adding custom swagger params
            @ApiParam(value = "Vacation Id",
                    required = true,
                    example = "1")
            @PathVariable long vacaid,
            HttpServletRequest request)
    {
        // logging
        logger.info(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed.");

        vacaService.delete(vacaid);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Adding custom swagger documentation for adding a new vacation
    @ApiOperation(value = "Add a new vacation",
            response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Vacation Created",
                    response = void.class),
            @ApiResponse(code = 400,
                    message = "Could Not Create Vacation",
                    response = ErrorDetail.class)})

    // POST:  /vacations
    @PostMapping(value = "/vacations",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addNewVaca(HttpServletRequest request,
                                        @Valid
                                        @RequestBody Vacations newvaca,
                                        Authentication authentication) throws URISyntaxException
    {
        // logger
        logger.trace(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed.");

        User user = userService.findByName(authentication.getName());
        newvaca = vacaService.save(newvaca, user);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{vacaid}")
                .buildAndExpand(newvaca.getVacaid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null,
                responseHeaders,
                HttpStatus.CREATED);
    }
}
