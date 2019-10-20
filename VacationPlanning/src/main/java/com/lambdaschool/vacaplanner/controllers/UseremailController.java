package com.lambdaschool.vacaplanner.controllers;

import com.lambdaschool.vacaplanner.logging.Loggable;
import com.lambdaschool.vacaplanner.models.ErrorDetail;
import com.lambdaschool.vacaplanner.models.Useremail;
import com.lambdaschool.vacaplanner.services.UseremailService;
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
// http://bw-vacaplanner.herokuapp.com/useremails
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

        return new ResponseEntity<>(allUserEmails,
                                    HttpStatus.OK);
    }

    // Adding custom swagger documentation for get User Email By Id
    @ApiOperation(value = "Return User Email By Id",
            response = Useremail.class)
    @ApiResponses(value = {@ApiResponse(code = 200,
            message = "Email Found",
            response = Useremail.class),
            @ApiResponse(code = 404,
                    message = "Email Not Found",
                    response = ErrorDetail.class)})

    // GET: /useremail/{useremailId}
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/useremail/{useremailId}",
                produces = {"application/json"})
    public ResponseEntity<?> getUserEmailById(HttpServletRequest request,
                                              @PathVariable Long useremailId)
    {
        // logger
        logger.trace(request.getMethod().toUpperCase()
                            + " " + request.getRequestURI() + " accessed.");

        Useremail ue = useremailService.findUseremailById(useremailId);

        return new ResponseEntity<>(ue, HttpStatus.OK);
    }

    // Adding custom swagger documentation for find User email By user Name
    @ApiOperation(value = "Return User Email By user Name",
            response = Useremail.class)
    @ApiResponses(value = {@ApiResponse(code = 200,
            message = "Email Found",
            response = Useremail.class),
            @ApiResponse(code = 404,
                    message = "Email Not Found",
                    response = ErrorDetail.class)})

    // GET:  /username/{userName}
    @GetMapping(value = "/username/{userName}",
                produces = {"application/json"})
    public ResponseEntity<?> findUseremailByuserName(HttpServletRequest request,
                                                     @PathVariable String userName)
    {
        // logger
        logger.trace(request.getMethod().toUpperCase()
                            + " " + request.getRequestURI() + " accessed.");

        List<Useremail> ues = useremailService
                                        .findByUserName(userName,
                                        request.isUserInRole("ADMIN"));

        return new ResponseEntity<>(ues, HttpStatus.OK);
    }

    // Adding custom swagger documentation for delete User Email By Id
    @ApiOperation(value = "Delete an Email",
            response = void.class)
    @ApiResponses(value = {@ApiResponse(code = 200,
            message = "Email Deleted",
            response = void.class),
            @ApiResponse(code = 500,
                    message = "Could Not Delete Email",
                    response = ErrorDetail.class)})

    // DELETE:  /useremail/{useremailid}
    @DeleteMapping("/useremail/{useremailid}")
    public ResponseEntity<?> deleteUserEmailById(
            // Adding custom swagger params
            @ApiParam(value = "Course Id",
                    required = true,
                    example = "1") HttpServletRequest request,
            @PathVariable long useremailid)
    {
        // logger
        logger.trace(request.getMethod().toUpperCase()
                            + " " + request.getRequestURI() + " accessed");

        useremailService.delete(useremailid,
                                request.isUserInRole("ADMIN"));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Adding custom swagger documentation for Update User Email
    @ApiOperation(value = "Update a user email",
            response = void.class)
    @ApiResponses(value = {@ApiResponse(code = 200,
            message = "User Email Updated",
            response = void.class),
            @ApiResponse(code = 500,
                    message = "Could Not Update User Email",
                    response = ErrorDetail.class)})

    // PUT:  /useremail/{useremailid}/email/{emailaddress}
    @PutMapping("/useremail/{useremailid}/email/{emailaddress}")
    public ResponseEntity<?> updateUserEmail(
                    // Adding custom swagger params for update student
                    @ApiParam(value = "User Email Id",
                            required = true,
                            example = "1")
                            HttpServletRequest request,
                    @PathVariable long useremailid,
                    @PathVariable String emailaddress)
    {
        // logger
        logger.trace(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed");

        useremailService.update(useremailid, emailaddress,
                                request.isUserInRole("ADMIN"));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // add email through user
}
