package io.rahulnanhore.controller;

import io.rahulnanhore.domain.UserRole;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String homeController(){
        return "Career Orbit: User Service " + UserRole.ROLE_ADMIN;
    }
}
