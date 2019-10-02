package by.novitsky.mongotest.controller;

import by.novitsky.mongotest.entity.User;
import by.novitsky.mongotest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/name/{name}")
    public List<User> getUser(@PathVariable String name){
        return userService.getUserByName(name);
    }

    @GetMapping("/lastName/{name}")
    public List<User> getUserByLastName(@PathVariable String name){
        return userService.getUserByLastName(name);
    }

}
