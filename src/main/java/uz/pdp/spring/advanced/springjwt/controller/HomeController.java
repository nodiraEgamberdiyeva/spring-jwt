package uz.pdp.spring.advanced.springjwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.spring.advanced.springjwt.security.JwtBuild;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    JwtBuild jwtBuild;
    @GetMapping
    public String getHomePage(){
        return jwtBuild.generateToken("user");
    }
}
