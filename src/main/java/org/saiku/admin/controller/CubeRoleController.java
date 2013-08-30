package org.saiku.admin.controller;

import java.util.List;

import javax.inject.Inject;

import org.saiku.admin.repository.CubeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller 
@Service
public class CubeRoleController {
    @Inject
    private CubeRepository cubeRepository;
  
    @RequestMapping(value = "/cuberole", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<String> findRole(@RequestParam(value="schema") String query) {
       return cubeRepository.getRoleCube(query);
    }

}
