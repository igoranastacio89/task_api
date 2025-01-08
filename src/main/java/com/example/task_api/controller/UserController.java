package com.example.task_api.controller;

import com.example.task_api.model.User;
import com.example.task_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        String response = userService.saveUser(user);
        return ResponseEntity.status(201).body(response); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) throws ExecutionException, InterruptedException {
        User user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody User user) throws ExecutionException, InterruptedException {
        String response = userService.updateUser(id, user);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) throws ExecutionException, InterruptedException {
        String response = userService.deleteUser(id);
        return ResponseEntity.ok(response); 
    }
}
