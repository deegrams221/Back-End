package com.lambdaschool.vacaplanner.controllers;

import com.lambdaschool.vacaplanner.logging.Loggable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// roles to handle:
//   User - people who can look up vacations, todos, comments.
//   Data - people who can update data on vacations, todos, comments.
//   ADMIN - people who can update data on users and otherwise have full access to the system.

@Loggable
@RestController
@RequestMapping("/roles")
public class RolesController
{
    // logger
    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);

}
