package com.lambdaschool.vacaplanner.controllers;

import com.lambdaschool.vacaplanner.logging.Loggable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Loggable
@RestController
@RequestMapping("/vacations")
public class VacationController
{
    // logger
    private static final Logger logger = LoggerFactory.getLogger(VacationController.class);

}