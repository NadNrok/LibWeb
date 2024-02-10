package com.example.weblibrary.controller;

import com.example.weblibrary.entity.Role;
import com.example.weblibrary.entity.User;
import com.example.weblibrary.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationAndLoginController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Форма авторизації");
        return "login-form";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("title", "Форма реєстрації");
        return "registration-form";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object>model) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB !=null){
            model.put("message", "User exist!");
            return "registration-form";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return "redirect:/login";
    }
}
