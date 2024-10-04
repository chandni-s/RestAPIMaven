package com.chandni.api.controller;

import com.chandni.api.model.User;
import com.chandni.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    // Injecting UserService into controller
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUser")
    /*
    GET call for getting a User by name
     */
    public User getUser(@RequestParam String name) {
        User result = this.userService.getUser(name);
        if (result != null) {
            return result;
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with name: " + name + " not found");
        }
    }

    @GetMapping("/getAllUsers")
    /*
    GET call to fetch all users or return appropriate error
     */
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @PostMapping("/addUser")
    /*
    Create a new user or return appropriate error
     */
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        try {
            this.userService.addUser(newUser);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User " + newUser.toString() + " could not be added");
        }
    }

    @PutMapping("/updateUser")
    /*
    Update an existing User or return appropriate error
     */
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        if (this.userService.updateUser(user) != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, "User " + user.toString() + " could not be updated");
        }
    }

    @DeleteMapping("/deleteUser")
    /*
    Delete an existing User or return appropriate error
     */
    public ResponseEntity<HttpStatus> deleteUser(@RequestParam String userName) {
        if (this.userService.deleteUser(userName)) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "User " + userName + " could not be deleted");
        }
    }


}
