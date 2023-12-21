package org.neZorinEgor.springmvc.controllers;

import org.neZorinEgor.springmvc.dao.UserDAO;
import org.neZorinEgor.springmvc.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserDAO userDAO;

    @Autowired
    public UserController(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("users", userDAO.showAllUsers());
        return "user/all";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("user", userDAO.findUserById(id));
        return "user/byID";
    }
    @GetMapping("/new")
    public  String createForm(@ModelAttribute("user") User user){
        return "user/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user){
        userDAO.create(user);
        return "redirect:/users";
    }
}
