package kz.spring.quiz_app.controllers;
import kz.spring.quiz_app.model.Options;
import kz.spring.quiz_app.model.Questions;
import kz.spring.quiz_app.model.Quiz;
import kz.spring.quiz_app.repositories.OptionRepository;
import kz.spring.quiz_app.repositories.QuizRepository;
import kz.spring.quiz_app.services.OptionService;
import kz.spring.quiz_app.services.QuestionService;
import kz.spring.quiz_app.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class QuizController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private OptionService optionService;

    @Autowired
    private QuizService quizService;

    @GetMapping(value = "/questions/{idshka}")
    public String quiz(Model model, @PathVariable(name = "idshka") Long id){

        System.out.println(id);
        Quiz quizList = quizService.getQuizById(id);
        model.addAttribute("quiz", quizList);
        List<Questions> questionsList = questionService.getQuestionsByQuiz(quizList);
//        List<Options> optionsList = optionService.findById(optId);
        model.addAttribute("voprosy", questionsList);
//        model.addAttribute("otvety", optionsList);

        return "questions";
    }


}
