package com.cw.littlefins_proj.controller;

import com.cw.littlefins_proj.model.User;
import com.cw.littlefins_proj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<Object> allUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable("id")Long userId) {
        Optional<User>checkUser=userService.findById(userId);
        if(checkUser.isEmpty()){
            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(checkUser.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable("id") Long userId, @RequestBody User user) {
        Optional<User> checkUser = userService.findById(userId).map(u -> {
            u.setUsername(user.getUsername());
            u.setEmail(user.getEmail());
            u.setPassword(user.getPassword());
            return userService.save(u);
        });
        if (checkUser.isEmpty()) {
            return new ResponseEntity<>("User not updated", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(checkUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Long userId) {
        Optional<User> checkUser = userService.findById(userId).map(u -> {
            userService.deleteById(u.getId());
            return u;
        });
        if (checkUser.isEmpty()) {
            return new ResponseEntity<>("User not deleted", HttpStatus.BAD_REQUEST);
        }
        String response = String.format("User %d deleted", checkUser.get().getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Object> countUser() {
        long count = userService.count();
        if (count <= 0) {
            return new ResponseEntity<>("No user found", HttpStatus.NOT_FOUND);
        }
        Map<String, Object> totalUser = new HashMap<>();
        totalUser.put("total", count);
        return new ResponseEntity<>(totalUser, HttpStatus.OK);
    }

}
