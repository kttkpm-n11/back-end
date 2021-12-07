package iuh.kttkpm.nhom11.controller;

import iuh.kttkpm.nhom11.entity.MessageResponse;
import iuh.kttkpm.nhom11.entity.User;
import iuh.kttkpm.nhom11.service.UserService;
import iuh.kttkpm.nhom11.ulti.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
        if (userService.findByUsername(user.getUsername())!=null)
            return ResponseEntity.badRequest().body(new MessageResponse("Username không được trùng"));
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");
        user.setRoles(roles);
        return ResponseEntity.ok(userService.createUser(user));
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        User userPrincipal = userService.findByUsername(user.getUsername());

        if (user == null || !new BCryptPasswordEncoder().matches(user.getPassword(),
                userPrincipal.getPassword())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Account or password is not valid");
        }

        String token = jwtUtil.generateToken(user);
        return ResponseEntity.ok(token);
    }




}
