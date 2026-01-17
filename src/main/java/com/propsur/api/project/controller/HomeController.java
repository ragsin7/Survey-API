package com.propsur.api.project.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.propsur.api.project.entity.TmUsers;
import com.propsur.api.project.service.UserServiceApi;

@RestController
@RequestMapping("/api/view/users")
public class HomeController {

    @Autowired
    UserServiceApi userService;
    
    @Autowired 
    UserServiceApi userServiceData;

    @GetMapping("/user")
    public List<TmUsers> getMapping(){
        return userService.getUsers();
    }

    @GetMapping("/current-user")
    public String getCurrentUser(Principal principal){
        return principal.getName();
    }

}
