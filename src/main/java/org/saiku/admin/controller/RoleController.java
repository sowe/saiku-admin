package org.saiku.admin.controller;

import java.util.List;

import javax.inject.Inject;


import org.saiku.admin.model.Role;
import org.saiku.admin.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller 
@RequestMapping("/api/roles")
@Service
public class RoleController {

    @Inject
    private RoleRepository roleRepository;

    static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json") 
    @ResponseBody
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json") 
    @ResponseStatus(HttpStatus.CREATED) 
    @ResponseBody
    public Role create(@RequestBody Role role) {
        return roleRepository.create(role);
    }
}
