package com.lambdaschool.vacationplanner.controllers;

import com.lambdaschool.vacationplanner.logging.Loggable;
import com.lambdaschool.vacationplanner.models.ErrorDetail;
import com.lambdaschool.vacationplanner.models.Role;
import com.lambdaschool.vacationplanner.services.RoleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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


// roles to handle:
//   User - people who can look up vacations, todos, comments.
//   Data - people who can update data on vacations, todos, comments.
//   ADMIN - people who can update data on users and otherwise have full access to the system.

@Loggable
@RestController
@RequestMapping("/roles")
// http://localhost:2019/roles
// http://bw-vacaplanning.herokuapp.com/roles
public class RolesController
{
    // logger
    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);

    @Autowired
    RoleService roleService;

    // Adding custom swagger documentation for list all roles
    @ApiOperation(value = "Return all Roles",
            response = Role.class,
            responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200,
            message = "Roles Found",
            response = Role.class),
            @ApiResponse(code = 404,
                    message = "Roles Not Found",
                    response = ErrorDetail.class)})

    // GET:  /roles
    @GetMapping(value = "/roles",
            produces = {"application/json"})
    public ResponseEntity<?> listRoles(HttpServletRequest request)
    {
        // logger
        logger.trace(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed.");

        List<Role> allRoles = roleService.findAll(Pageable.unpaged());

        return new ResponseEntity<>(allRoles, HttpStatus.OK);
    }

    // Adding custom swagger documentation for get Role By Id
    @ApiOperation(value = "Return Role By Id",
            response = Role.class,
            responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200,
            message = "Role Found",
            response = Role.class),
            @ApiResponse(code = 404,
                    message = "Role Not Found",
                    response = ErrorDetail.class)})

    // GET:  /role/{roleId}3
    @GetMapping(value = "/role/{roleId}",
            produces = {"application/json"})
    public ResponseEntity<?> getRoleById(HttpServletRequest request,
                                         @PathVariable Long roleId)
    {
        // logger
        logger.trace(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed.");

        Role r = roleService.findRoleById(roleId);

        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    // Adding custom swagger documentation for get Role By Name
    @ApiOperation(value = "Return Role By Name",
            response = Role.class,
            responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200,
            message = "Role Found",
            response = Role.class),
            @ApiResponse(code = 404,
                    message = "Role Not Found",
                    response = ErrorDetail.class)})

    // GET:  /role/name/{roleName}
    @GetMapping(value = "/role/name/{roleName}",
            produces = {"application/json"})
    public ResponseEntity<?> getRoleByName(HttpServletRequest request,
                                           @PathVariable String roleName)
    {
        logger.trace(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed.");

        Role r = roleService.findByName(roleName);

        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    // Adding custom swagger documentation for add New Role
    @ApiOperation(value = "Add a new role",
            response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Role Added",
                    response = void.class),
            @ApiResponse(code = 500,
                    message = "Could Not Add Role",
                    response = ErrorDetail.class)})

    // POST:  /role
    @PostMapping(value = "/role")
    public ResponseEntity<?> addNewRole(
            HttpServletRequest request,
            @Valid
            @RequestBody Role newRole) throws URISyntaxException
    {
        // logger
        logger.trace(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed.");

        newRole = roleService.save(newRole);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newRoleURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{roleid}")
                .buildAndExpand(newRole.getRoleid())
                .toUri();
        responseHeaders.setLocation(newRoleURI);

        return new ResponseEntity<>(null,
                responseHeaders, HttpStatus.CREATED);
    }

    // Adding custom swagger documentation for update user
    @ApiOperation(value = "Update a user",
            response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "User Information Updated",
                    response = void.class),
            @ApiResponse(code = 500,
                    message = "Could Not Update User",
                    response = ErrorDetail.class)})

    // PUT:  /role/{roleid}
    @PutMapping(value = "/role/{roleid}")
    public ResponseEntity<?> updateRole(
            // Adding custom swagger params
            @ApiParam(value = "Role Id",
                    required = true,
                    example = "1")
                    HttpServletRequest request,
            @PathVariable long roleid,
            @Valid
            @RequestBody Role updateRole) throws URISyntaxException
    {
        // logger
        logger.trace(request.getMethod().toUpperCase()
                + " " + request.getRequestURI() + " accessed.");

        roleService.update(roleid, updateRole);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Adding custom swagger documentation for delete Role By Id
    @ApiOperation(value = "Delete a role by id",
            response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Role Deleted",
                    response = void.class),
            @ApiResponse(code = 500,
                    message = "Could Not Delete Role",
                    response = ErrorDetail.class)})

    // DELETE:  /role/{id}
    @DeleteMapping("/role/{id}")
    public ResponseEntity<?> deleteRoleById(
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

        roleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
