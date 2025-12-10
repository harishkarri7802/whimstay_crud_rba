package com.whimstay.controller.user;

import com.whimstay.dto.RegisterRequest;
import com.whimstay.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping
    public String home(){
        return "Home";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest registerRequest){
        boolean isRegister = userService.register(registerRequest);
        if(!isRegister){
            return new ResponseEntity<>("user is not register", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("register successfully", HttpStatus.OK);
    }
}
