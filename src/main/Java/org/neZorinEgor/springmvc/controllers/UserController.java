package org.neZorinEgor.springmvc.controllers;

import org.neZorinEgor.springmvc.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
