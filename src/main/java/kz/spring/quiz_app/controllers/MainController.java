package kz.spring.quiz_app.controllers;

import kz.spring.quiz_app.model.User;
import kz.spring.quiz_app.services.QuizService;
import kz.spring.quiz_app.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    private final UsersService usersService;
    private final QuizService quizService;

    @Autowired
    public MainController(UsersService usersService, QuizService quizService) {
        this.usersService = usersService;
        this.quizService = quizService;
    }

    @GetMapping("/")
    public String home(Model m) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        Optional<User> person = usersService.findByUsername(currentPrincipalName);

        if (person.get().getRole().equals("ROLE_ADMIN")) {
            List<User> users = usersService.findAll();
            m.addAttribute("users", users);

            return "admin/adminPage.html";
        }
        else if (person.get().getRole().equals("ROLE_TEACHER")) {

            return "teacher/teacher.html";
        }
        else if (person.get().getRole().equals("ROLE_USER")) {
            m.addAttribute("allQuiz", quizService.getAllQuiz());

            return "student/index.html";
        }
        else {
            return "student/index.html";
        }
    }
}
