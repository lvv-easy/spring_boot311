package com.example.demo.controller;


import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class MyController {

    private final UserService userService;

    @Autowired
    public MyController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String showIndexPage(Model model, Principal principal) {
        System.out.println("simple - " + principal.getName());
        model.addAttribute("users", userService.index());
        return "index";
    }

    @GetMapping("/user")
    public String showOneUserPage(Model model, Authentication authentication) {
        model.addAttribute("user", userService.findPersonByEmail(((User) authentication.getPrincipal()).getEmail()));
        return "show";
    }

    @GetMapping("/admin/new")
    public String showNewUserPage(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping("/admin")
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "new";
        }

        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/edit")
    public String showEditUserPage(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("user", userService.show(id));
        return "edit";
    }

    @PatchMapping("admin/{id}")
    public String UpdateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "edit";
        }

        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
