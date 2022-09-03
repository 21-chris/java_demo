package com.example.controller;

import com.example.common.Result;
import com.example.pojo.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User findUserById(@PathVariable(value = "id") int id){
        User user = userService.findUserById(id);
        return user;
    }
    @PostMapping("/regist")
    public int insertUser(@RequestBody User user){
        return userService.insertUser(user);
    }

    @GetMapping("/hello")
//    @PreAuthorize("hasAuthority('test')")
    @PreAuthorize("admin")
    public String Hello(){
        System.out.println("he;l");
        return "heoop";
    }
    @PostMapping("/update")
    public int insert(@RequestBody User user){
        return userService.updateUser(user);
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        return userService.loginUser(user);
    }

}
