package com.springmvc.controllers;

import com.springmvc.models.User;
import com.springmvc.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("user", userService.getAllUsers());
        return "users/users";
    }

    @GetMapping("/new")
    public String addUser (@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping()
    public String saveUser (@ModelAttribute ("user") User user) {
        userService.addUser (user);
        return "redirect:/user";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String removeUser(@PathVariable ("id") long id) {
        userService.removeUser(id);
        return "redirect:/user";
    }

    @GetMapping("/edit/{id}")
    public String editUser(Model model, @PathVariable ("id") long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute ("user") User user, @PathVariable("id") long id){
        userService.updateUser(user);
        return "redirect:/user";
    }
}
