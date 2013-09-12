package org.saiku.admin.controller;

import javax.inject.Inject;

import org.saiku.admin.model.mysql.Status;
import org.saiku.admin.utils.ParserMysqlAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller 
@RequestMapping("/api/status")
@Service
public class StatusController {

    static final Logger logger = LoggerFactory.getLogger(StatusController.class);

    @Inject
    private ParserMysqlAdmin parser;

    
    @RequestMapping(method = RequestMethod.GET, produces = "application/json") 
    @ResponseBody
    public Status findAll() {

    	return parser.status();
    }

}
