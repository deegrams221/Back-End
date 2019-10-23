package com.lambdaschool.vacationplanner.controllers;

import com.lambdaschool.vacationplanner.logging.Loggable;
import com.lambdaschool.vacationplanner.models.ErrorDetail;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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


    // Adding custom swagger documentation for find All Vacations
    @ApiOperation(value = "find all Vacations",
            response = Vacations.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201,
                    message = "Found Vacations",
                    response = void.class),
            @ApiResponse(code = 500,
                    message = "Vacations Not Found",
                    response = ErrorDetail.class)})

    // GET:  /vacations/vacations
    @GetMapping(value = "/vacations", produces = {"application/json"})
    public ResponseEntity<?> findAllVacas(HttpServletRequest request)
    {
        // logger
        logger.info(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed.");

        return new ResponseEntity<>(
                vacaService.findAllVacas(), HttpStatus.OK);
    }


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

    // GET: /vacations/vacations/{vacaid}
    @GetMapping(value = "/vacations/{vacaid}",
            produces = {"application/json"})
    public ResponseEntity<?> findVacationById(HttpServletRequest request,
                                              @PathVariable long vacaid)
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

    // GET: /vacations/vacations/{place}
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

    // DELETE /vacations/vacations/{vacaid}
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
//    @ApiOperation(value = "Add a new vacation",
//            response = void.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200,
//                    message = "Vacation Created",
//                    response = void.class),
//            @ApiResponse(code = 400,
//                    message = "Could Not Create Vacation",
//                    response = ErrorDetail.class)})

    // POST:  /vacations/vacations
    //    {
    //        "place": "Hawaii",
    //    }
    //@PreAuthorize("hasAuthority('ROLE_USER')")
//    @PostMapping(value = "/vacations",
//            consumes = {"application/json"})
//    public ResponseEntity<?> addNewVaca(HttpServletRequest request,
//                                        @Valid
//                                        @RequestBody Vacations newvaca)
//    {
//        // logger
//        logger.trace(request.getMethod().toUpperCase()
//                + " " + request.getRequestURI() + " accessed.");
//
//       // newvaca = vacaService.save(newvaca);
//        vacaService.save(newvaca);
//
//        // set the location header for the newly created resource
////        HttpHeaders responseHeaders = new HttpHeaders();
////        URI newVacationURI = ServletUriComponentsBuilder.fromUriString(request.getServerName()
////                + ":" + request.getLocalPort() + "/vacations/vacations/{vacaid}")
////                .buildAndExpand(newvaca.getVacaid()).toUri();
////        responseHeaders.setLocation(newVacationURI);
//
////        return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
//        return new ResponseEntity<>(HttpStatus.CREATED);



    @ApiOperation(value = "Creates a new vacation and assigns it to logged in user",
            response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201,
                    message = "Vacation Created",
                    response = void.class),
            @ApiResponse(code = 500,
                    message = "Could Not Create Vacation",
                    response = ErrorDetail.class)})

    // POST:  /vacations/newvaca
    //    {
    //        "place": "Hawaii"
    //    }
    @PostMapping(value = "/newvaca",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> createVacation(@Valid
                                            @RequestBody Vacations newvaca) throws URISyntaxException
    {
        newvaca =  vacaService.save(newvaca);
        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newVacaURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{vacaid}")
                .buildAndExpand(newvaca.getVacaid())
                .toUri();
        responseHeaders.setLocation(newVacaURI);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
}
