package com.lambdaschool.vacationplanner.controllers;

import com.lambdaschool.vacationplanner.logging.Loggable;
import com.lambdaschool.vacationplanner.models.ErrorDetail;
import com.lambdaschool.vacationplanner.models.Useremail;
import com.lambdaschool.vacationplanner.services.UseremailService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Loggable
@RestController
@RequestMapping("/useremails")
// http://localhost:2019/useremails
// http://bw-vacaplanning.herokuapp.com/useremails
public class UseremailController
{
    // logger
    private static final Logger logger = LoggerFactory.getLogger(UseremailController.class);

    @Autowired
    UseremailService useremailService;

    // Adding custom swagger documentation for list All User emails
    @ApiOperation(value = "Return a list of all User emails",
            response = Useremail.class)
    @ApiResponses(value = {@ApiResponse(code = 200,
            message = "List Found",
            response = Useremail.class),
            @ApiResponse(code = 404,
                    message = "List Not Found",
                    response = ErrorDetail.class)})

    // GET:  /useremails
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/useremails",
            produces = {"appliacation/json"})
    public ResponseEntity<?> listAllUseremails(HttpServletRequest request)
    {
        // logger
        logger.trace(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed.");

        List<Useremail> allUserEmails = useremailService.findAll();

        return new ResponseEntity<>(allUserEmails, HttpStatus.OK);
    }
}
