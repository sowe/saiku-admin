package org.saiku.admin.controller;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;


import org.saiku.admin.model.User;
import org.saiku.admin.repository.ConfRepository;
import org.saiku.admin.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller 
@RequestMapping("/api/users")
public class UserController {

    @Inject
    private UserRepository userRepository;
    @Inject
    private ConfRepository confRepository;

    static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json") 
    @ResponseBody
    public List<User> findAll() {

    	logger.info(confRepository.findFirst().toString());

    	logger.info(confRepository.findFirst().getSaikuUsers());
    	
    	return userRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json") 
    @ResponseStatus(HttpStatus.CREATED) 
    @ResponseBody
    public User create(@RequestBody User user) throws FileNotFoundException, UnsupportedEncodingException {

    	user.setCreateDate(new Date());
    	user = userRepository.create(user);

    	List<User> users = userRepository.findAll();

    	// TODO, change path file  logger.info(confRepository.findFirst().toString());
    	PrintWriter writer = new PrintWriter("/users.properties", "UTF-8");
		writer.println("#Username,password,role");
    	for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
			User user2 = iterator.next();

			writer.println(user2.getUser()+"="+user2.getPassword()+","+user2.rolesToString());
		}

    	writer.close();


    	user.setCreateDate(new Date());

        return user;
    }
    
}
