package kz.spring.quiz_app.controllers;
import kz.spring.quiz_app.model.Questions;
import kz.spring.quiz_app.model.Quiz;
import kz.spring.quiz_app.model.Session;
import kz.spring.quiz_app.model.User;
import kz.spring.quiz_app.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class QuizController {

    private final QuestionService questionService;
    private final OptionService optionService;
    private final QuizService quizService;
    private final UsersService usersService;
    private final SessionService sessionService;

    Date start;

    @Autowired
    public QuizController(QuestionService questionService, OptionService optionService, QuizService quizService, UsersService usersService, SessionService sessionService) {
        this.questionService = questionService;
        this.optionService = optionService;
        this.quizService = quizService;
        this.usersService = usersService;
        this.sessionService = sessionService;
    }

    @GetMapping(value = "/questions/{idshka}")
    public String quiz(Model model, @PathVariable(name = "idshka") Long id){
        Quiz quiz = quizService.getQuizById(id);
        model.addAttribute("quiz", quiz);
        start = new Date();

        return "student/questions";
    }

    @PostMapping(value = "/submit")
    public String postQuiz(Model model,@ModelAttribute Quiz quiz){

        Date end = new Date();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User user = usersService.getByUsername(currentPrincipalName);
        int score = sessionService.getScore(quiz);

        Session session = new Session(user, quiz, score, start, end);
        sessionService.saveSession(session);
        model.addAttribute("result", session);
        model.addAttribute("cviz", quiz);
        return "student/result";
    }

    @GetMapping(value = "/sessions")
    public String sessions(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User user = usersService.getByUsername(currentPrincipalName);
        model.addAttribute("mySessions", sessionService.findByUserId(user.getId()));

        return "student/sessions";
    }
}
