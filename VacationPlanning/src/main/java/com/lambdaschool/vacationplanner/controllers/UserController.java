package com.lambdaschool.vacationplanner.controllers;

import com.lambdaschool.vacationplanner.logging.Loggable;
import com.lambdaschool.vacationplanner.models.ErrorDetail;
import com.lambdaschool.vacationplanner.models.User;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Loggable
@RestController
@RequestMapping("/users")
// http://localhost:2019/users
// http://bw-vacaplanning.herokuapp.com/users
public class UserController
{
    // logger
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    // Adding custom swagger documentation for list all users with paging/sorting
    @ApiOperation(value = "Return all Users",
            response = User.class,
            responseContainer = "List")
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
    // GET:  /users/?page=1&size=1
    // GET:  /users/?sort=username,desc&sort=<field>,asc
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/users",
            produces = {"application/json"})
    public ResponseEntity<?> listAllUsers(HttpServletRequest request,
                                          @PageableDefault(page = 0, size = 5)
                                                  Pageable pageable)
    {
        // logger
        logger.trace(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed.");

        List<User> myUsers = userService.findAll(pageable);

        return new ResponseEntity<>(myUsers, HttpStatus.OK);
    }

    // Adding custom swagger documentation for list all users
    @ApiOperation(value = "Return all Users",
            response = User.class,
            responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200,
            message = "User List Found",
            response = User.class),
            @ApiResponse(code = 404,
                    message = "User List Not Found",
                    response = ErrorDetail.class)})

    // GET:  /users/all
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/users/all",
            produces = {"application/json"})
    public ResponseEntity<?> reallyListAllUsers(HttpServletRequest request)
    {
        // logger
        logger.trace(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed");

        List<User> myUsers = userService.findAll(Pageable.unpaged());

        return new ResponseEntity<>(myUsers, HttpStatus.OK);
    }

    // Adding custom swagger documentation for Find User By Id
    @ApiOperation(value = "Find User By Id",
            response = User.class)
    @ApiResponses(value = {@ApiResponse(code = 200,
            message = "User Found",
            response = User.class),
            @ApiResponse(code = 404,
                    message = "User Not Found",
                    response = ErrorDetail.class)})

    // GET:  /user/{userId}
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/user/{userId}",
            produces = {"application/json"})
    public ResponseEntity<?> getUserById(HttpServletRequest request,
                                         @PathVariable Long userId)
    {
        // logger
        logger.trace(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed.");

        User u = userService.findUserById(userId);

        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    // Adding custom swagger documentation for Find User By Name
    @ApiOperation(value = "Find User By Name",
            response = User.class)
    @ApiResponses(value = {@ApiResponse(code = 200,
            message = "User Name Found",
            response = User.class),
            @ApiResponse(code = 404,
                    message = "User Name Not Found",
                    response = ErrorDetail.class)})

    // GET:  /user/name/{userName}
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/user/name/{userName}",
            produces = {"application/json"})
    public ResponseEntity<?> getUserByName(
            // Adding custom swagger params for Find User By Name
            @ApiParam(value = "User Name",
                    required = true,
                    example = "Tifa")
                    HttpServletRequest request,
            @PathVariable String userName)
    {
        // logger
        logger.trace(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed.");

        User u = userService.findByName(userName);

        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    // Adding custom swagger documentation for list all users with names containing a given string with paging/sorting
    @ApiOperation(value = "returns all Users with names containing a given string",
            response = User.class,
            responseContainer = "List")
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

    // GET:  /user/name/like/da?sort=username
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/user/name/like/{userName}",
            produces = {"application/json"})
    public ResponseEntity<?> getUserLikeName(HttpServletRequest request,
                                             @PathVariable String userName,
                                             @PageableDefault(page = 0,
                                                     size = 5)
                                                     Pageable pageable)
    {
        // logger
        logger.trace(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed.");

        List<User> u = userService.findByNameContaining(userName, pageable);

        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    // Adding custom swagger documentation for get Current Username
    @ApiOperation(value = "Find Current Username",
            response = User.class)
    @ApiResponses(value = {@ApiResponse(code = 200,
            message = "Current Username Found",
            response = User.class),
            @ApiResponse(code = 404,
                    message = "Current Username Not Found",
                    response = ErrorDetail.class)})

    // GET: /getusername
    @GetMapping(value = "/getusername",
            produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> getCurrentUsername(HttpServletRequest request,
                                                Authentication authentication)
    {
        // logger
        logger.trace(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed.");

        return new ResponseEntity<>(authentication.getPrincipal(), HttpStatus.OK);
    }

    // Adding custom swagger documentation for get user info
    @ApiOperation(value = "Find Current User Information",
            response = User.class)
    @ApiResponses(value = {@ApiResponse(code = 200,
            message = "Current User Information Found",
            response = User.class),
            @ApiResponse(code = 404,
                    message = "Current User Information Not Found",
                    response = ErrorDetail.class)})

    // GET:  /getuserinfo
    @GetMapping(value = "/getuserinfo",
            produces = {"application/json"})
    public ResponseEntity<?> getCurrentUserInfo(HttpServletRequest request,
                                                Authentication authentication)
    {
        // logger
        logger.trace(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed.");

        User u = userService.findByName(authentication.getName());

        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    // Adding custom swagger documentation for add new user
    @ApiOperation(value = "Add a new user",
            notes = "New user Id will be sent to the location header",
            response = void.class)
    @ApiResponses(value = {@ApiResponse(code = 201,
            message = "User Added",
            response = void.class),
            @ApiResponse(code = 500,
                    message = "Could Not Add User",
                    response = ErrorDetail.class)})

    // POST:  /user
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/user",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addNewUser(HttpServletRequest request,
                                        @Valid
                                        @RequestBody User newuser) throws URISyntaxException
    {
        // logger
        logger.trace(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed.");

        newuser = userService.save(newuser);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newuser.getUserid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null,
                responseHeaders,
                HttpStatus.CREATED);
    }

    // Adding custom swagger documentation for update user
    @ApiOperation(value = "Update a user",
            response = void.class)
    @ApiResponses(value = {@ApiResponse(code = 200,
            message = "User Information Updated",
            response = void.class),
            @ApiResponse(code = 500,
                    message = "Could Not Update User",
                    response = ErrorDetail.class)})

    // PUT:  /user/{id}
    @PutMapping(value = "/user/{id}")
    public ResponseEntity<?> updateUser(
            // Adding custom swagger params
            @ApiParam(value = "User Id",
                    required = true,
                    example = "1")
                    HttpServletRequest request,
            @RequestBody User updateUser,
            @PathVariable long id)
    {
        // logger
        logger.trace(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed.");

        userService.update(updateUser, id, request.isUserInRole("ADMIN"));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Adding custom swagger documentation for delete User By Id
    @ApiOperation(value = "Delete a user by id",
            response = void.class)
    @ApiResponses(value = {@ApiResponse(code = 200,
            message = "User Deleted",
            response = void.class),
            @ApiResponse(code = 500,
                    message = "Could Not Delete User",
                    response = ErrorDetail.class)})

    // DELETE:  /user/{id}
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUserById(
            // Adding custom swagger params
            @ApiParam(value = "User Id",
                    required = true,
                    example = "1")
                    HttpServletRequest request,
            @PathVariable long id)
    {
        // logger
        logger.trace(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed.");

        userService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Adding custom swagger documentation for delete User Role By Id
    @ApiOperation(value = "Delete a user's role by id",
            response = void.class)
    @ApiResponses(value = {@ApiResponse(code = 200,
            message = "User Role Deleted",
            response = void.class),
            @ApiResponse(code = 500,
                    message = "Could Not Delete User Role",
                    response = ErrorDetail.class)})

    // DELETE:  /user/{userid}/role/{roleid}
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/user/{userid}/role/{roleid}")
    public ResponseEntity<?> deleteUserRoleByIds(
            // Adding custom swagger params
            @ApiParam(value = "User Id",
                    required = true,
                    example = "1")
                    HttpServletRequest request,
            @PathVariable long userid,
            @PathVariable long roleid)
    {
        // logger
        logger.trace(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed.");

        userService.deleteUserRole(userid, roleid);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Adding custom swagger documentation for adding a User Role By Id
    @ApiOperation(value = "Add a user's role by id",
            response = void.class)
    @ApiResponses(value = {@ApiResponse(code = 200,
            message = "User Role Added",
            response = void.class),
            @ApiResponse(code = 500,
                    message = "Could Not Add User Role",
                    response = ErrorDetail.class)})

    // POST:  /user/{userid}/role/{roleid}
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/user/{userid}/role/{roleid}")
    public ResponseEntity<?> postUserRoleByIds(
            HttpServletRequest request,
            @PathVariable long userid,
            @PathVariable long roleid)
    {
        // logger
        logger.trace(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed.");

        userService.addUserRole(userid, roleid);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
