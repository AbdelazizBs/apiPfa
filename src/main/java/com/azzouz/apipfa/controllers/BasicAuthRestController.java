package com.azzouz.apipfa.controllers;

import com.azzouz.apipfa.entities.AuthenticationBean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://127.0.0.1:4200")
@RestController
public class BasicAuthRestController {
    @GetMapping(path = "/basicauth")
    public AuthenticationBean basicauth(@RequestParam(value = "username") final String username, @RequestParam(value = "password") final String password) {
        return new AuthenticationBean("You are authenticated");
    }
}

