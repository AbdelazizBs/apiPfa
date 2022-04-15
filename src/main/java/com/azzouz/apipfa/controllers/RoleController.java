package com.azzouz.apipfa.controllers;
/*
import com.azzouz.apipfa.entities.Role;
import com.azzouz.apipfa.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/roles")
public class RoleController {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleController(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public boolean addRole(final String role) {
        final Role user = new Role(1,"USER");
        final  Role admin = new Role(2,"ADMIN");
        roleRepository.save(user);
        roleRepository.save(admin);
        return true;
    }

}*/