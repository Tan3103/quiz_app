package kz.spring.quiz_app.controllers;

import jakarta.validation.Valid;
import kz.spring.quiz_app.model.User;
import kz.spring.quiz_app.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UsersService usersService;

    @Autowired
    public AdminController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/update/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", usersService.findOne(id));
        return "admin/update";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        usersService.delete(id);
        return "redirect:/";
    }

    @PatchMapping("/update/{id}")
    public String update(@ModelAttribute("person") @Valid User person, BindingResult bindingResult,
                         @PathVariable("id") int id) {

//        if (bindingResult.hasErrors())
//            return "people/edit";

        usersService.update(id, person);
        return "redirect:/";
    }
}
