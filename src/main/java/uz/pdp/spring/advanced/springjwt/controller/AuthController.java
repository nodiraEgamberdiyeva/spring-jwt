package uz.pdp.spring.advanced.springjwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.spring.advanced.springjwt.config.SecurityConfiguration;
import uz.pdp.spring.advanced.springjwt.payload.UserDto;
import uz.pdp.spring.advanced.springjwt.security.JwtBuild;
import uz.pdp.spring.advanced.springjwt.service.MyAuthService;

@RestController
@RequestMapping("/api/user")
public class AuthController {

    @Autowired
    private MyAuthService myAuthService;
    @Autowired
    private JwtBuild jwtBuild;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;



    @PostMapping
    public HttpEntity<?> login(@RequestBody UserDto userDto){
//        UserDetails userDetails = myAuthService.loadUserByUsername(userDto.getUsername());

//        boolean matches = passwordEncoder.matches(userDto.getPassword(), userDetails.getPassword());  //match manually
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
            return ResponseEntity.status(200).body(jwtBuild.generateToken(userDto.getUsername()));
        }
        catch (BadCredentialsException ex){
            return ResponseEntity.status(409).body("");
        }
    }
}
